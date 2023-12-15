package madasi.sensorManager.service;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import madasi.sensorManager.model.Sensor;
import madasi.sensorManager.model.SensorData;
import madasi.sensorManager.util.CustomUtil;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDataRepository sensorDataRepository;
    
    @Autowired
    private ProducerService producerService;
    
    @Autowired
    private HdfsService hdfsService;
    
    public static final String SENSOR_TOPIC = "sensor_data_transmission";

	Logger logger = LoggerFactory.getLogger(ProducerService.class);
	
    @Scheduled(fixedRate = 3600000) // 3600000 milliseconds = 1 hour
    public void processAndSendSensorData() {
        sendAndDeleteData();
    }

	/**
	 * @return
	 */
	public void sendAndDeleteData() {
		// Get current time
        String sensorDataJson = "";
        List<SensorData> sdList = new ArrayList<>();

        try {
        	// Serialize and send each sensor data to Kafka. Can send all at once in a big json or each individually ?
        	for(SensorData sd : sensorDataRepository.findAll()) {
        		sd.setSensor_mac_tmp(sd.getSensor().getMac());
        		sdList.add(sd);
        	}
        	sensorDataJson = CustomUtil.convertObjectToJson(sdList);
        	
        	saveDataToHdfs(sensorDataJson);
        	
        	sensorDataRepository.deleteAll();
        }catch(Exception e) {
        	logger.error("sendAndDeleteData", e);
        }
	}
	
	
	public void saveDataToHdfs(String jsonData) throws InterruptedException {
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    String pattern = "yyyy_MM_dd_HH_mm_ss"; // Custom date time format so the file name is valid
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);

	    String hdfsPath = "/user/hive/warehouse/sensor/data_" + sdf.format(timestamp) + ".csv"; // Replace with your HDFS path
	    logger.info("Trying to write to path: {}", hdfsPath);

	    try {
	        String csvData = convertJsonToCsv(jsonData);
	        logger.info("Data: {}", csvData);
	        
//	        hdfsService.writeCsvToHdfs(hdfsPath, csvData);
	        hdfsService.writeFileToHDFS(hdfsPath, csvData);
	        
	        System.out.println("CSV data successfully written to HDFS");
	    } catch (IOException e) {
	        logger.error("saveDataToHdfs error: ", e);
	    }
	}

	private String convertJsonToCsv(String jsonData) throws IOException {
	    ObjectMapper jsonMapper = new ObjectMapper();
	    CsvMapper csvMapper = new CsvMapper();
	    CsvSchema schema = csvMapper.schemaFor(SensorData.class).withHeader();

	    List<SensorData> dataList = jsonMapper.readValue(jsonData, new TypeReference<List<SensorData>>() {});
	    return csvMapper.writer(schema).writeValueAsString(dataList);
	}


    @KafkaListener(topics = SENSOR_TOPIC)
    public void onSensorDataReceived(String message) {
        // Deserialize the message to SensorData object
    	logger.info(message);
    	SensorData sensorData = null;
    	if(message != null) {
    		try {
    			sensorData = (SensorData) CustomUtil.convertJsonToObject(message, SensorData.class);
    		}catch(Exception e) {
    			logger.error("Failed to deserialize sensor data. Error: ", e);
    		}
    	}
        
        if(sensorData != null) {
        	// Check if the sensor exists, if not, create and save a new one
        	Sensor sensor = null;
        	if(sensorData.getSensor() != null) {
        		sensor = findOrCreateSensor(sensorData.getSensor().getMac());
        	}else {
        		sensor = findOrCreateSensor(sensorData.getSensor_mac_tmp());
        	}
        	
        	// Link sensor with sensor data
        	sensorData.setSensor(sensor);
        	
        	// Save the sensor data to the database
        	sensorDataRepository.save(sensorData);
        }
    }

    private Sensor findOrCreateSensor(String macAddress) {
        return sensorRepository.findByMac(macAddress)
                .orElseGet(() -> {
                    Sensor newSensor = new Sensor();
                    newSensor.setMac(macAddress);
                    // Set other properties of Sensor if necessary
                    return sensorRepository.save(newSensor);
                });
    }

    // Additional methods if needed
}

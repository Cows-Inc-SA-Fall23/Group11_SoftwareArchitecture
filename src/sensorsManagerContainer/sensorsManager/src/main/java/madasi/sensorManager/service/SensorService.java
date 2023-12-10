package madasi.sensorManager.service;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import madasi.sensorManager.model.Sensor;
import madasi.sensorManager.model.SensorData;
import madasi.sensorManager.util.CustomUtil;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDataRepository sensorDataRepository;
    
    @Autowired
    private ProducerService producerService;
    
    public static final String SENSOR_TOPIC = "sensor_data_transmission";

	Logger logger = LoggerFactory.getLogger(ProducerService.class);
	

    // constructor with @Autowired dependencies

    @Scheduled(fixedRate = 3600000) // 3600000 milliseconds = 1 hour
    public void processAndSendSensorData() {
        // Get current time
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        // Fetch sensor data from the database
        List<SensorData> sensorDataList = sensorDataRepository.findByTimestampBefore(currentTime);

        // Serialize and send each sensor data to Kafka. Can send all at once in a big json or each individually ?
        sensorDataList.forEach(data -> {
            String message = CustomUtil.convertObjectToJson(data);
            producerService.sendMessage("sensor_data_hdfs", message);
        });

        // Delete sent data from the database
        sensorDataRepository.deleteAll(sensorDataList);
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

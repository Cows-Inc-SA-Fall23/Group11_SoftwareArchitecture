package madasi.sensorManager.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import madasi.sensorManager.model.Livestock;
import madasi.sensorManager.model.Sensor;
import madasi.sensorManager.model.SensorData;
import madasi.sensorManager.model.Setting;
import madasi.sensorManager.service.ProducerService;
import madasi.sensorManager.service.SensorDataRepository;
import madasi.sensorManager.service.SensorRepository;
import madasi.sensorManager.service.SensorService;
import madasi.sensorManager.service.SettingRepository;
import madasi.sensorManager.service.UserRepository;
import madasi.sensorManager.util.CustomUtil;

@Controller
public class HomeController {
	
	@Autowired
    private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Value("${project.name}")
	public String PROJECT_NAME;
	
	@Autowired
	private SensorService sensorService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SettingRepository settingRepository;
	
	@Autowired
	private SensorRepository sensorRepository;
	
	@Autowired
	private SensorDataRepository sensorDataRepository;
	
	@Autowired
	ProducerService producerService;

	// executes on startup
	@PostConstruct
	void onStartup() {
		try {
			logger.info("HELLO");
			logger.info(PROJECT_NAME);
			Optional<Setting> s = settingRepository.findById(1);
			Setting setupSet;
			if (s.isEmpty()) {
				setupSet = new Setting();
				setupSet.setId(1);
				setupSet.setProjectName(PROJECT_NAME);
				settingRepository.save(setupSet);
			} else {
				setupSet = s.get();
			}
			setupSet.setProjectName(PROJECT_NAME);
			settingRepository.save(setupSet);
			logger.info(setupSet.toString());

		} catch (Exception e) {
			logger.error("On startup error: ", e);
		}
	}

	// automatically ads settings object to pages
	@ModelAttribute
	public void addAttributes(Model model) {
		Optional<Setting> s = settingRepository.findById(1);
		model.addAttribute("setting", s.get());
	}

	// if you want the home page to be something else.
//	@RequestMapping("/")

	@RequestMapping("/")
	public String home(Model model, HttpSession session, Locale locale) {
		String localeTitle = messageSource.getMessage("home.title", null, locale);
		model.addAttribute("title", localeTitle);

		return "home";
	}

	@RequestMapping("/debug")
	public String debug(Model model, HttpSession session) {
		return "debug";
	}
	
	@RequestMapping("/sensorDataList")
	public String sensorDataList(Model model, HttpSession session) {
		model.addAttribute("sensorDataList", sensorDataRepository.findAll());
		return "sensorDataList";
	}

	@RequestMapping("/sensors")
	public String sensors(Model model, HttpSession session) throws InterruptedException, ExecutionException, TimeoutException, JsonMappingException, JsonProcessingException {
		model.addAttribute("sensorList", sensorRepository.findAll());
		
		List<Livestock> livestock = new ArrayList<>();
		
		String liveStockString = CustomUtil.sendAndReceiveKafkaMessage("", "livestock_data_request", producerService);
		ObjectMapper objectMapper = new ObjectMapper();
		livestock = objectMapper.readValue(liveStockString, new TypeReference<List<Livestock>>() {});
		
		logger.info(liveStockString);
		model.addAttribute("livestockList", livestock);
		
		
		return "sensors";
	}
	
	@PostMapping("/sendSensorDataHadoop")
	public ResponseEntity<?> sendSensorDataHadoop(){
		try {
			sensorService.sendAndDeleteData();
			return ResponseEntity.ok("Sensor data sent successfully");
		}catch(Exception e) {
            return ResponseEntity.status(500).body("Error sending sensor data");
		}
	}
	
	@PostMapping("/sendRandomSensorData")
    public ResponseEntity<?> sendRandomSensorData(@RequestParam("valueName") String valueName,
                                                  @RequestParam("value") String value,
                                                  @RequestParam("sensorMac") String sensorMac) {
        
        try {
            SensorData tempSensor = new SensorData();
            tempSensor.setTimestamp(new Timestamp(System.currentTimeMillis()));
            tempSensor.setValueName(valueName);
            tempSensor.setValue(value);
            tempSensor.setId(-1);
            tempSensor.setSensor_mac_tmp(sensorMac);

            producerService.sendMessage(SensorService.SENSOR_TOPIC, CustomUtil.convertObjectToJson(tempSensor));
            return ResponseEntity.ok("Sensor data sent successfully");
        } catch (Exception e) {
            // Handle exceptions as needed
            return ResponseEntity.status(500).body("Error sending sensor data");
        }
    }
	
	@PostMapping("/saveSensorLivestockAssignments")
    public String saveSensorLivestockAssignments(@RequestParam Map<String, String> allParams) throws InterruptedException, ExecutionException, TimeoutException, JsonMappingException, JsonProcessingException {
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String mac = entry.getKey().replace("livestock_", "");
            Integer livestockId = Integer.valueOf(entry.getValue());

            Sensor sensor = sensorRepository.findByMac(mac).orElse(null);
            if (sensor != null) {
            	
        		sensor.setLivestock(livestockId);
                sensorRepository.save(sensor);
                
            }
        }
        return "redirect:/sensors"; // Redirect back to the sensors page
    }

	
}
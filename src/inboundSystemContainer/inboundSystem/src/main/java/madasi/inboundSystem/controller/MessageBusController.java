package madasi.inboundSystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import madasi.inboundSystem.service.ProducerService;
import madasi.inboundSystem.util.CustomUtil;

@Controller
public class MessageBusController {
	

	Logger logger = LoggerFactory.getLogger(MessageBusController.class);

	@Value("${project.name}")
	public String PROJECT_NAME;

	@Autowired
	private ProducerService producerService;

	@PostMapping("/send")
    public String sendMessageToKafkaTopic(@RequestBody String topic,@RequestBody String message) {
		producerService.sendMessage(topic, message);
        return "Message sent successfully";
    }
	
	@GetMapping("/testGetData")
	public ResponseEntity<String> testGetData(){
		ResponseEntity<String> response = new ResponseEntity<>("",HttpStatus.NOT_FOUND);
		
		int numberOfCopies = 5;
		
		sendMessageToKafkaTopic("request_test_data", CustomUtil.convertObjectToJson(numberOfCopies));
		
		return response;
	}
}
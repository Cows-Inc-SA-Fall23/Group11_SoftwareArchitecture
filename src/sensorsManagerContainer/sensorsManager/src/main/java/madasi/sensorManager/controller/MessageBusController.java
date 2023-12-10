package madasi.sensorManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import madasi.sensorManager.service.ProducerService;
import madasi.sensorManager.util.CustomUtil;


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
	/*
	 * @GetMapping({"/testGetData", "/testGetData/"}) public ResponseEntity<String>
	 * testGetData() throws Exception{ return testGetData(0); }
	 * 
	 * @GetMapping("/testGetData/{number}") public ResponseEntity<String>
	 * testGetData(@PathVariable("number") Integer number) throws Exception {
	 * 
	 * String numberOfCopies = number.toString();
	 * 
	 * return
	 * ResponseEntity.ok(CustomUtil.sendAndReceiveKafkaMessage(numberOfCopies,
	 * "silo_data_request", producerService)); }
	 */
	
	/**
	 * @param stringToSend
	 * @param requestId
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
}
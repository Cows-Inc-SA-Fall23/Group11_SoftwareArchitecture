package madasi.controlPanel.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import madasi.controlPanel.service.ProducerService;
import madasi.controlPanel.util.CustomUtil;

import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class MessageBusController {
	

	Logger logger = LoggerFactory.getLogger(MessageBusController.class);
	
	 private static Map<String, CompletableFuture<String>> requestMap = new ConcurrentHashMap<>();
	 
	 public CompletableFuture<String> getFromRequestMap(String id) {
		 return requestMap.get(id);
	 }
	 
	 public CompletableFuture<String> removeFromRequestMap(String id) {
		 return requestMap.remove(id);
	 }

	@Value("${project.name}")
	public String PROJECT_NAME;

	@Autowired
	private ProducerService producerService;

	@PostMapping("/send")
    public String sendMessageToKafkaTopic(@RequestBody String topic,@RequestBody String message) {
		producerService.sendMessage(topic, message);
        return "Message sent successfully";
    }

	@GetMapping({"/testGetData", "/testGetData/"})
	public ResponseEntity<String> testGetData() throws Exception{
		return testGetData(0);
	}
	
	@GetMapping("/testGetData/{number}")
	public ResponseEntity<String> testGetData(@PathVariable("number") Integer number) throws Exception {
		
	    String numberOfCopies = number.toString();
	    String requestId = UUID.randomUUID().toString(); // Unique identifier for the request
	    CompletableFuture<String> future = new CompletableFuture<>();

	    // Store the future object in a map or similar structure, keyed by requestId
	    requestMap.put(requestId, future);
	    // Send message to Kafka, including the requestId
	    String requestPayload = CustomUtil.convertObjectToJson(new ArrayList<>(Arrays.asList(numberOfCopies, requestId)));
	    sendMessageToKafkaTopic("silo_data_request", requestPayload);

	    // Wait for the response
	    String response = future.get(500, TimeUnit.SECONDS); // Timeout after 30 seconds

	    return ResponseEntity.ok(response);
	}
}
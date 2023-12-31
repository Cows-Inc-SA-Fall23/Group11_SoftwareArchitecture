package madasi.feedSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import madasi.feedSystem.controller.SiloController;
import madasi.feedSystem.util.CustomUtil;
@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private String payload = null;
    
    @Autowired
    SiloController siloController;
    
    @Autowired
    ProducerService producerService;

    /**
     * when an event happens, we can reference other controllers/services here. 
     * @param message
     */
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    public void listen(String message) {
        payload = message;
        latch.countDown();
    }

    @SuppressWarnings("unchecked")
	@KafkaListener(topics = "silo_data_request")
    public void siloListen(String message) {
        // Extract the requestId from the message
        // For simplicity, let's assume the message is in the format "requestId:data"
    	List<String> parts = new ArrayList<>();
    	parts = (List<String>) CustomUtil.convertJsonToObject(message, List.class);
    	
    	Integer messageRepeatForTesting = Integer.parseInt(parts.get(0));
        String requestId = parts.get(1);
        
        // Process the request and generate a response
        String silosJson = siloController.getAllSilos().toString();
        
        List<String> responseList = new ArrayList<>();
        responseList.add(requestId);
        responseList.add(silosJson);

        // Send the response with the requestId
        producerService.sendMessage("silo_data_response", CustomUtil.convertObjectToJson(responseList));
    }


    public CountDownLatch getLatch() {
        return latch;
    }

    /**
     * This just gets the latest message.
     * @return
     */
    public String getPayload() {
        return payload;
    }

    // Reset the latch and payload, allowing the service to be reused for another test
    public void resetLatch(int count) {
        latch = new CountDownLatch(count);
        payload = null;
    }
}

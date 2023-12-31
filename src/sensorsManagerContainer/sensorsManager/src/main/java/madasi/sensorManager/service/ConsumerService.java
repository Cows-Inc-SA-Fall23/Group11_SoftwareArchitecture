package madasi.sensorManager.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import madasi.sensorManager.util.CustomUtil;

@Service
public class ConsumerService {

    @SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private String payload = null;
    
    /**
     * when an event happens, we can reference other controllers/services here. 
     * @param message
     */
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    public void listen(String message) {
        payload = message;
        latch.countDown();
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
    
    @KafkaListener(topics = "livestock_data_response")	//could use a generic topic for all responses, since they've got their own ids anyways
    public void onLivestockDataReceived(String message) {
        // Extract the requestId and response from the message
        // For simplicity, let's assume the message is in the format "requestId:responseData"
    	@SuppressWarnings("unchecked")
    	List<String> list = (List<String>) CustomUtil.convertJsonToObject(message, List.class);
    	String requestId = list.get(1);
        String responseData = list.get(0);

        CompletableFuture<String> future = CustomUtil.getFromRequestMap(requestId);
        if (future != null) {
            future.complete(responseData);
            CustomUtil.removeFromRequestMap(requestId);
        }
    }
}

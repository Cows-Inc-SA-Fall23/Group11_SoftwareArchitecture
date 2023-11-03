package madasi.controlPanel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private String payload = null;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    public void listen(String message) {
//        logger.info("Received message in group foo: {}", message);
        payload = message;
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    // Reset the latch and payload, allowing the service to be reused for another test
    public void resetLatch(int count) {
        latch = new CountDownLatch(count);
        payload = null;
    }
}

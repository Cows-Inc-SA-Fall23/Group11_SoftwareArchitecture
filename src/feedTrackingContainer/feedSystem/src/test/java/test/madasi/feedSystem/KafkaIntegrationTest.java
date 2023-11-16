package test.madasi.feedSystem;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import madasi.feedSystem.FeedSystemApplication;
import madasi.feedSystem.service.ConsumerService;
import madasi.feedSystem.service.ProducerService;


@SpringBootTest(classes = FeedSystemApplication.class) // This will load your full Spring context based on your main application
@ExtendWith(SpringExtension.class)
//@EmbeddedKafka(partitions = 1, topics = {KafkaIntegrationTest.TEST_TOPIC})
public class KafkaIntegrationTest {

    static final String TEST_TOPIC = "test-topic";

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    @Timeout(10)
    public void givenEmbeddedKafkaBroker_whenSendingToSimpleProducer_thenMessageReceived() throws Exception {
        // Arrange
        String data = "Sending with own simple KafkaProducer";

        // Act
        producerService.sendMessage(TEST_TOPIC, data);

        // Assert
        consumerService.getLatch().await();
        
        assertThat(consumerService.getLatch().getCount()).isEqualTo(0);
        assertThat(consumerService.getPayload()).contains(data);
    }
}

package test.madasi.controlPanel;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import madasi.controlPanel.service.ConsumerService;
import madasi.controlPanel.service.ProducerService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class DddApplicationTests {

    @Autowired
    private ProducerService producer;

    @Autowired
    private ConsumerService consumer;

    @Autowired
    private KafkaTemplate<String, String> template;

    private static final String TEST_TOPIC = "test-topic";

    @Test
    public void testSendReceive() throws Exception {
        String message = "Hello Kafka!";
        producer.sendMessage(TEST_TOPIC, message);

        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(consumer.getLatch().getCount()).isEqualTo(0);
        assertThat(consumer.getPayload()).contains(message);
    }
}

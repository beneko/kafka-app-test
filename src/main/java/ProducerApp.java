import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerApp {

    private static int counter = 0;
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "test-client-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        Random random = new Random();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            System.out.println("------------Sending messages-------------");
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic-1", "key-" + counter++, "value-" + random.nextDouble() * 1000);
            producer.send(record,
            (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("Message sent successfully");
                    System.out.println("Topic: " + metadata.topic());
                    System.out.println("Partition: " + metadata.partition());
                    System.out.println("Offset: " + metadata.offset());
                    System.out.println("Timestamp: " + metadata.timestamp());
                } else {
                    System.out.println("Error while sending message");
                    exception.printStackTrace();
                    producer.close();
                }
            });

        }, 1000, 1000, TimeUnit.MILLISECONDS);

    }
}

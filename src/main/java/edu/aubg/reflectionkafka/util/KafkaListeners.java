package edu.aubg.reflectionkafka.util;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "topic",
            groupId = "topic_group"
    )
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}

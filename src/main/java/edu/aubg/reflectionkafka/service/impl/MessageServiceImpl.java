package edu.aubg.reflectionkafka.service.impl;

import edu.aubg.reflectionkafka.service.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private String latestMessage;

    public MessageServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishMessage(String topic, String message) {
         kafkaTemplate.send(topic, message);
    }

    @Override
    public String consumeMessage(String topic) {
        return latestMessage;
    }

    @KafkaListener(
            topics = "topic",
            groupId = "topic_group"
    )
    private void listener(String message) {
        latestMessage = message;
    }
}

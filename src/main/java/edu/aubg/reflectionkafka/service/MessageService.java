package edu.aubg.reflectionkafka.service;

public interface MessageService {

    void publishMessage(String topic, String message);

    String consumeMessage(String topic);
}

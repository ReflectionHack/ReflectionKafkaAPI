package edu.aubg.reflectionkafka.controller;

import edu.aubg.reflectionkafka.model.MessageRequest;
import edu.aubg.reflectionkafka.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message/{topic}")
    public ResponseEntity<String> getMessage(@PathVariable String topic) {
        return ResponseEntity.ok(messageService.consumeMessage(topic));
    }

    @PostMapping("/message/{topic}")
    public void publish(@PathVariable String topic, @RequestBody MessageRequest request) {
        messageService.publishMessage(topic, request.message());
    }


}

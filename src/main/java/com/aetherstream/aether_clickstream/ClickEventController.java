package com.aetherstream.aether_clickstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class ClickEventController {

    // This KafkaTemplate is used to send messages to Kafka.
    @Autowired
    private KafkaTemplate<String, ClickEvent> kafkaTemplate;

    // POST endpoint to receive click events from the frontend
    @PostMapping("/click")
    public ResponseEntity<Void> handleClick(@RequestBody ClickEvent event) {
        // Sends the click event to the Kafka topic "user-click-events"
        kafkaTemplate.send("user-click-events", event);
        // Responds with an HTTP 202 Accepted status
        return ResponseEntity.accepted().build();
    }
}

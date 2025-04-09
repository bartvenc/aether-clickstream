package com.aetherstream.aether_clickstream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.aetherstream.aether_clickstream.ClickEvent;

@Component
public class ClickEventConsumer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // KafkaListener listens to the "user-click-events" topic.
    @KafkaListener(topics = "user-click-events", groupId = "aether-clickstream-group")
    public void consumeClickEvent(ClickEvent event) {
        // Log the event for debugging purposes.
        System.out.println("Consumed click event: " + event);

        // Insert the event into the PostgreSQL database.
        String sql = "INSERT INTO clicks (item_id, item_type, timestamp) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, event.getItemId(), event.getItemType(), event.getTimestamp());
    }
}

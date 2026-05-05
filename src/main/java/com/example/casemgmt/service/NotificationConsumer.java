package com.example.casemgmt.service;

import com.example.casemgmt.entity.CaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    @KafkaListener(topics = "case-events", groupId = "notification-group")
    public void handleCaseEvent(CaseEvent event) {
        log.info("Received case event: {} -> {}", event.caseId(), event.status());
        // Send email/SMS logic here
    }
}
package com.example.casemgmt.service;

import com.example.casemgmt.dto.CaseDto;
import com.example.casemgmt.dto.CreateCaseDto;
import com.example.casemgmt.entity.Case;
import com.example.casemgmt.entity.CaseEvent;
import com.example.casemgmt.entity.CaseStatus;
import com.example.casemgmt.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository repository;
    private final KafkaTemplate<String, CaseEvent> kafkaTemplate;

    public CaseDto create(CreateCaseDto dto) {
        Case caseEntity = new Case();
        caseEntity.setCustomerId(dto.customerId());
        caseEntity.setPriority(dto.priority());
        caseEntity.setDescription(dto.description());

        Case saved = repository.save(caseEntity);

        // Publish Kafka event
        kafkaTemplate.send("case-events", saved.getId().toString(),
                new CaseEvent(saved.getId(), saved.getStatus()));

        return mapToDto(saved);
    }

    public CaseDto updateStatus(UUID id, CaseStatus status) {
        Case caseEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found: " + id));

        caseEntity.setStatus(status);
        Case saved = repository.save(caseEntity);

        kafkaTemplate.send("case-events", id.toString(),
                new CaseEvent(id, status));

        return mapToDto(saved);
    }

    private CaseDto mapToDto(Case c) {
        return new CaseDto(
                c.getId(),
                c.getCustomerId(),
                c.getStatus(),
                c.getPriority(),
                c.getDescription(),
                c.getCreatedAt()
        );
    }
}
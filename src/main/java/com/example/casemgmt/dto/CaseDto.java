package com.example.casemgmt.dto;

import com.example.casemgmt.entity.CaseStatus;
import com.example.casemgmt.entity.Priority;
import java.time.LocalDateTime;
import java.util.UUID;

public record CaseDto(
        UUID id,
        UUID customerId,
        CaseStatus status,
        Priority priority,
        String description,
        LocalDateTime createdAt
) {}
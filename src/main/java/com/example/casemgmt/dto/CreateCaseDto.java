package com.example.casemgmt.dto;

import com.example.casemgmt.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateCaseDto(
        @NotNull UUID customerId,
        @NotNull Priority priority,
        @NotBlank String description
) {}
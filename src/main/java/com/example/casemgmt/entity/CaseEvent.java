package com.example.casemgmt.entity;

import java.util.UUID;

public record CaseEvent(UUID caseId, CaseStatus status) {}
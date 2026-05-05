package com.example.casemgmt.controller;

import com.example.casemgmt.dto.CaseDto;
import com.example.casemgmt.dto.CreateCaseDto;
import com.example.casemgmt.entity.CaseStatus;
import com.example.casemgmt.service.CaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @PostMapping
    public ResponseEntity<CaseDto> create(@Valid @RequestBody CreateCaseDto dto) {
        CaseDto result = caseService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CaseDto> updateStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, CaseStatus> body) {
        CaseStatus status = body.get("status");
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }
        CaseDto result = caseService.updateStatus(id, status);
        return ResponseEntity.ok(result);
    }
}
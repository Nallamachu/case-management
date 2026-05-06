package com.softifyo.mgmt.controller;

import com.softifyo.mgmt.dto.CaseDto;
import com.softifyo.mgmt.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    @Autowired
    CaseService caseService;

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<CaseDto>> getAllCases() {
        List<CaseDto> caseDtoList = caseService.getAllCases();
        return (!caseDtoList.isEmpty()) ? ResponseEntity.ok(caseDtoList) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<CaseDto> getCaseById(Integer id) {
        CaseDto caseDto = caseService.getCaseById(id);
        return (caseDto != null) ? ResponseEntity.ok(caseDto) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CaseDto> createCase(CaseDto caseDto) {
        CaseDto createdCase = caseService.createCase(caseDto);
        return (createdCase != null) ? ResponseEntity.ok(createdCase) : ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CaseDto> updateCase(Integer id, CaseDto caseDto) {
        CaseDto updatedCase = caseService.updateCase(id, caseDto);
        return (updatedCase != null) ? ResponseEntity.ok(updatedCase) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteCase(Integer id) {
        try {
            caseService.deleteCase(id);
            return ResponseEntity.ok("Case deleted with id "+id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting case with id "+id+": " + e.getMessage());
        }
    }
}

package com.softifyo.mgmt.service;

import com.softifyo.mgmt.dto.CaseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseService {
        CaseDto createCase(CaseDto caseDto);
        CaseDto getCaseById(Integer id);
        List<CaseDto> getAllCases();
        CaseDto updateCase(Integer id, CaseDto caseDto);
        void deleteCase(Integer id);
}

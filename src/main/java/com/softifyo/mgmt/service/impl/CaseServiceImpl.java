package com.softifyo.mgmt.service.impl;

import com.softifyo.mgmt.dto.CaseDto;
import com.softifyo.mgmt.entity.Case;
import com.softifyo.mgmt.exception.BadRequestException;
import com.softifyo.mgmt.repository.CaseRepository;
import com.softifyo.mgmt.service.CaseService;
import com.softifyo.mgmt.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    MapperUtil mapperUtil;

    @Override
    public CaseDto createCase(CaseDto caseDto) {
        try{
            Case caseEntity = mapperUtil.mapToEntity(caseDto, Case.class);
            caseEntity = caseRepository.save(caseEntity);
            if(caseEntity.getId() != null){
                System.out.println("Case created successfully with ID: " + caseEntity.getId());
                return mapperUtil.mapToDto(caseEntity, CaseDto.class);
            } else {
                System.out.println("Failed to create case.");
            }
        } catch (Exception e){
            throw new BadRequestException("Error creating case: " + e.getMessage());
        }
        return null;
    }

    @Override
    public CaseDto getCaseById(Integer id) {
        Optional<Case> caseOptional = caseRepository.findById(id);
        if(caseOptional.isPresent()){
            return mapperUtil.mapToDto(caseOptional.get(), CaseDto.class);
        } else {
            throw new BadRequestException("Case not found with ID: " + id);
        }
    }

    @Override
    public List<CaseDto> getAllCases() {
        List<Case> caseList = caseRepository.findAll();
        if(!caseList.isEmpty()){
            return mapperUtil.mapToDtoList(caseList, CaseDto.class);
        } else {
            throw new BadRequestException("No cases found.");
        }
    }

    @Override
    public CaseDto updateCase(Integer id, CaseDto caseDto) {
            try {
                Case existingCase = caseRepository.findById(id)
                        .orElseThrow(() -> new BadRequestException("Case not found with ID: " + id));

                existingCase.setTitle(caseDto.getTitle());
                existingCase.setDescription(caseDto.getDescription());
                existingCase.setStatus(caseDto.getStatus());

                existingCase = caseRepository.save(existingCase);
                return mapperUtil.mapToDto(existingCase, CaseDto.class);
            } catch (Exception e) {
                throw new BadRequestException("Error updating case: " + e.getMessage());
            }
    }

    @Override
    public void deleteCase(Integer id) {
        if(!caseRepository.existsById(id)){
            throw new BadRequestException("Case not found with ID: " + id);
        }
        caseRepository.deleteById(id);
    }

}

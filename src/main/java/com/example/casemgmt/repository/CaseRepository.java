package com.example.casemgmt.repository;

import com.example.casemgmt.entity.Case;
import com.example.casemgmt.entity.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CaseRepository extends JpaRepository<Case, UUID> {
    List<Case> findByCustomerIdAndStatus(UUID customerId, CaseStatus status);
}
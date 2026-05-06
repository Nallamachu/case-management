package com.softifyo.mgmt.repository;

import com.softifyo.mgmt.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {
}

package com.nolimit.demo.repository;

import com.nolimit.demo.entity.JurisdictionConfig;
import com.nolimit.demo.entity.JurisdictionConfigId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JurisdictionConfigRepository extends JpaRepository<JurisdictionConfig, JurisdictionConfigId> {
}

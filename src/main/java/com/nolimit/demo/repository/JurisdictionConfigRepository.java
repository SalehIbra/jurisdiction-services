package com.nolimit.demo.repository;

import com.nolimit.demo.entity.Configuration;
import com.nolimit.demo.entity.Jurisdiction;
import com.nolimit.demo.entity.JurisdictionConfig;
import com.nolimit.demo.entity.JurisdictionConfigId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JurisdictionConfigRepository extends JpaRepository<JurisdictionConfig, JurisdictionConfigId> {

    Optional<JurisdictionConfig> findByJurisdictionAndConfiguration(Jurisdiction newJurisdiction, Configuration newConfiguration);

}

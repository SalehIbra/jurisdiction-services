package com.nolimit.demo.repository;

import com.nolimit.demo.entity.Jurisdiction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JurisdictionRepository extends CrudRepository<Jurisdiction, Long> {

    Optional<Jurisdiction> findByCode(String code);

}

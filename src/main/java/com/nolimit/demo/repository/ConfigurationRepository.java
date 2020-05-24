package com.nolimit.demo.repository;

import com.nolimit.demo.entity.Configuration;
import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<Configuration, Long> {
}

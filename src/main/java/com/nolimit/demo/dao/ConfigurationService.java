package com.nolimit.demo.dao;

import com.nolimit.demo.entity.Configuration;
import com.nolimit.demo.entity.Jurisdiction;
import com.nolimit.demo.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    ConfigurationRepository configurationRepository;

    public List<Configuration> getAllConfiguration() {
        return (List<Configuration>) configurationRepository.findAll();
    }

    public Configuration getConfigurationById(Long configId) throws NoSuchFieldException {
        Optional<Configuration> configuration = configurationRepository.findById(configId);
        if (configuration.isPresent()) {
            return configuration.get();
        } else {
            throw new NoSuchFieldException("No confiquration with the selected key");
        }
    }

}

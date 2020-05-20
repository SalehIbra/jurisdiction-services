package com.nolimit.demo.dao;

import com.nolimit.demo.entity.Configuration;
import com.nolimit.demo.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {

    @Autowired
    ConfigurationRepository configurationRepository;

    public List<Configuration> getAllConfiguration(){
        return (List<Configuration>) configurationRepository.findAll();
    }
}

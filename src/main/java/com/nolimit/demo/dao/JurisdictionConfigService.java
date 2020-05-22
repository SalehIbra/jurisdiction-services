package com.nolimit.demo.dao;

import com.nolimit.demo.entity.Configuration;
import com.nolimit.demo.entity.Jurisdiction;
import com.nolimit.demo.entity.JurisdictionConfig;
import com.nolimit.demo.entity.JurisdictionConfigId;
import com.nolimit.demo.repository.JurisdictionConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JurisdictionConfigService {

    @Autowired
    JurisdictionConfigRepository jurisdictionConfigRepository;

    public List<JurisdictionConfig> getAllJurisdictionConfig(){
        List<JurisdictionConfig> jurisdictionConfigs = jurisdictionConfigRepository.findAll();
        if (jurisdictionConfigs.size() > 0 ){
            return jurisdictionConfigs;
        } else {
            return new ArrayList<JurisdictionConfig>();
        }
    }

    public String saveJurisdictionConfig(JurisdictionConfig jurisdictionConfig){

        Jurisdiction newJurisdiction = jurisdictionConfig.getJurisdiction();
        Configuration newConfiguration = jurisdictionConfig.getConfiguration();
        Optional<JurisdictionConfig> newJurisdictionConfig = jurisdictionConfigRepository.findByJurisdictionAndConfiguration(newJurisdiction,newConfiguration);
        jurisdictionConfigRepository.save(jurisdictionConfig);
        if(newJurisdictionConfig.isPresent()){
            return "Configuration value updated";
        } else {
            return "New Configuration value added";
        }
    }

    public void deleteJurisdictionConfig(Long jurId, Long configId) throws NoSuchFieldException {
        JurisdictionConfigId jurisdictionConfigId = new JurisdictionConfigId(jurId,configId);
        Optional<JurisdictionConfig> jurisdictionConfig = jurisdictionConfigRepository.findById(jurisdictionConfigId);
        if(jurisdictionConfig.isPresent()){
            jurisdictionConfigRepository.deleteById(jurisdictionConfigId);
        } else {
            throw new NoSuchFieldException("No record with the selected code and key is found");
        }
    }

}

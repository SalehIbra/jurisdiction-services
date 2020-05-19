package com.nolimit.demo.dao;

import com.nolimit.demo.entity.Jurisdiction;
import com.nolimit.demo.repository.JurisdictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JurisdictionService {

    @Autowired
    JurisdictionRepository jurisdictionRepository;

    public List<Jurisdiction> getAllJurisdictions(){

        return (List<Jurisdiction>) jurisdictionRepository.findAll();
    }

    public Jurisdiction saveJurisdiction(Jurisdiction jurisdiction){
        Optional<Jurisdiction> jurisdictionByCode = jurisdictionRepository.findByCode(jurisdiction.getCode());
        if(jurisdictionByCode.isPresent()){
            Jurisdiction newJurisdiction = jurisdictionByCode.get();
            newJurisdiction.setName(jurisdiction.getName());
            newJurisdiction = jurisdictionRepository.save(newJurisdiction);
            return newJurisdiction;

        }else {
          jurisdiction = jurisdictionRepository.save(jurisdiction);
          return jurisdiction;

        }
    }

}

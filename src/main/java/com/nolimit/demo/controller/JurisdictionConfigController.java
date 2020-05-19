package com.nolimit.demo.controller;

import com.nolimit.demo.dao.JurisdictionService;
import com.nolimit.demo.entity.Jurisdiction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class JurisdictionConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JurisdictionConfigController.class);

    @Autowired
    JurisdictionService jurisdictionService;


    @RequestMapping(path = "/")
    public String viewMainPage(){

        return "home";
    }

// get method to get our jurisdiction list and render it in viewer
    @RequestMapping(path = "/list")
    public String getAllJurisdictions(Model model){
        List<Jurisdiction> jurisdictionList = jurisdictionService.getAllJurisdictions();
        model.addAttribute("jurisdictions",jurisdictionList);
        model.addAttribute("newJurisdiction",new Jurisdiction());
        LOGGER.info("Load jurisdiction list from db ...");
        return "jurisdiction";
    }
// save new jurisdiction in the database and render it in viewer
    @RequestMapping(path = "/saveJurisdiction", method = RequestMethod.POST)
    public String saveJurisdiction(@ModelAttribute("newJurisdiction") Jurisdiction newJurisdiction){
      jurisdictionService.saveJurisdiction(newJurisdiction);
      return "redirect:/list";
    }


}

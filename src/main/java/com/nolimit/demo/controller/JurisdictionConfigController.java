package com.nolimit.demo.controller;

import com.nolimit.demo.dao.ConfigurationService;
import com.nolimit.demo.dao.JurisdictionConfigService;
import com.nolimit.demo.dao.JurisdictionService;
import com.nolimit.demo.entity.Configuration;
import com.nolimit.demo.entity.Jurisdiction;
import com.nolimit.demo.entity.JurisdictionConfig;
import org.eclipse.jetty.http.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class JurisdictionConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JurisdictionConfigController.class);

    @Autowired
    JurisdictionService jurisdictionService;

    @Autowired
    JurisdictionConfigService jurisdictionConfigService;

    @Autowired
    ConfigurationService configurationService;

    @RequestMapping(path = "/")
    public String viewMainPage(Model model){
        List<JurisdictionConfig> jurisdictionConfigs = jurisdictionConfigService.getAllJurisdictionConfig();
        model.addAttribute("jurisdictionConfigs",jurisdictionConfigs);
        LOGGER.info("Load jurisdiction configuration list from database ...");
        return "home";
    }

    // get method to get our jurisdiction list and render it in viewer
    @RequestMapping(path = "/list")
    public String getAllJurisdictions(Model model){
        List<Jurisdiction> jurisdictionList = jurisdictionService.getAllJurisdictions();
        model.addAttribute("jurisdictions",jurisdictionList);
        model.addAttribute("newJurisdiction",new Jurisdiction());
        LOGGER.info("Load jurisdiction list from jurisdiction table in db ...");
        return "jurisdiction";
    }
    // save new jurisdiction in the database and render it in viewer
    @RequestMapping(path = "/saveJurisdiction", method = RequestMethod.POST)
    public String saveJurisdiction(@ModelAttribute("newJurisdiction") Jurisdiction newJurisdiction){
        jurisdictionService.saveJurisdiction(newJurisdiction);
        LOGGER.info("New jurisdiction code added ...");
        return "redirect:/list";
    }

    // create new page to add jurisdiction config
    @RequestMapping(path = "/new")
    public String newJurisdictionConfig(Model model,@ModelAttribute(value = "message") String message){
        List<Jurisdiction> jurisdictionList = jurisdictionService.getAllJurisdictions();
        List<Configuration> configurationList = configurationService.getAllConfiguration();
        model.addAttribute("newJurConfig", new JurisdictionConfig());
        model.addAttribute("jurisdictionList", jurisdictionList);
        model.addAttribute("configurationList", configurationList);
        model.addAttribute("message",message);
        LOGGER.info("Start adding or updating jurisdiction configuration value ...");
        return "add_jur_config";
    }

    @RequestMapping(path = "/saveJurisdictionConfig", method = RequestMethod.POST)
    public String saveJurisdictionConfig(@ModelAttribute("configValue") JurisdictionConfig configValue, RedirectAttributes attributes){
        JurisdictionConfig newJurisdictionConfig = new JurisdictionConfig(configValue.getJurisdiction(),configValue.getConfiguration()
                ,configValue.getValue(),configValue.getComment());
        String performedAction = jurisdictionConfigService.saveJurisdictionConfig(newJurisdictionConfig);
        attributes.addFlashAttribute("message",performedAction);
        LOGGER.info(performedAction);
        return "redirect:/new";
    }

}

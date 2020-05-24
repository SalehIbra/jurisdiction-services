package com.nolimit.demo.controller;

import com.nolimit.demo.dao.ConfigurationService;
import com.nolimit.demo.dao.JurisdictionConfigService;
import com.nolimit.demo.dao.JurisdictionService;
import com.nolimit.demo.entity.Configuration;
import com.nolimit.demo.entity.Jurisdiction;
import com.nolimit.demo.entity.JurisdictionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class JurisdictionConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JurisdictionConfigController.class);

    @Autowired
    JurisdictionService jurisdictionService;

    @Autowired
    JurisdictionConfigService jurisdictionConfigService;

    @Autowired
    ConfigurationService configurationService;

    @RequestMapping("/home")
    public String viewMainPage(Model model) {
        List<JurisdictionConfig> jurisdictionConfigs = jurisdictionConfigService.getAllJurisdictionConfig();
        model.addAttribute("jurisdictionConfigs", jurisdictionConfigs);
        LOGGER.info("Load jurisdiction configuration list from database ...");
        return "home";
    }

    // get method to get our jurisdiction list and render it in viewer
    @RequestMapping(path = "/list")
    public String getAllJurisdictions(Model model) {
        List<Jurisdiction> jurisdictionList = jurisdictionService.getAllJurisdictions();
        model.addAttribute("jurisdictions", jurisdictionList);
        model.addAttribute("newJurisdiction", new Jurisdiction());
        LOGGER.info("Load jurisdiction list from jurisdiction table in db ...");
        return "jurisdiction-list";
    }

    // create jurisdiction
    @RequestMapping(path = "/newJurisdiction")
    public String newJurisdictionConfig(Model model) {
        Jurisdiction newJurisdiction = new Jurisdiction();
        model.addAttribute("newJurisdiction", newJurisdiction);
        return "add-jurisdiction";
    }

    // save new jurisdiction in the database and render it in viewer
    @RequestMapping(path = "/saveJurisdiction", method = RequestMethod.POST)
    public String saveJurisdiction(@ModelAttribute("newJurisdiction") @Valid Jurisdiction newJurisdiction, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.info("Binding jurisdiction result error");
            return "add-jurisdiction";
        }
        jurisdictionService.saveJurisdiction(newJurisdiction);
        LOGGER.info("New jurisdiction code added ...");
        return "redirect:/list";
    }

    // create new page to add jurisdiction config
    @RequestMapping(path = "/newValue")
    public String newJurisdictionConfig(Model model, @ModelAttribute(value = "message") String message) {

        model.addAttribute("newJurConfig", new JurisdictionConfig());
        model.addAttribute("message", message);
        LOGGER.info("Start adding or updating jurisdiction configuration value ...");
        return "add-jur-config";
    }

    @RequestMapping(path = "/saveJurisdictionConfig", method = RequestMethod.POST)
    public String saveJurisdictionConfig(@ModelAttribute("newJurConfig") @Valid JurisdictionConfig newJurConfig, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            LOGGER.info("Binding jurisdiction configuration result error");
            return "add-jur-config";
        }
        JurisdictionConfig newJurisdictionConfig = new JurisdictionConfig(newJurConfig.getJurisdiction(), newJurConfig.getConfiguration()
                , newJurConfig.getValue(), newJurConfig.getComment());
        String performedAction = jurisdictionConfigService.saveJurisdictionConfig(newJurisdictionConfig);
        attributes.addFlashAttribute("message", performedAction);
        LOGGER.info(performedAction);
        return "redirect:/newValue";
    }

    @RequestMapping(path = "/deleteJurConfig/{jurId}/{configId}")
    public String deleteJurisdictionConfig(@PathVariable("jurId") Long jurId, @PathVariable("configId") Long configId) throws NoSuchFieldException {
        jurisdictionConfigService.deleteJurisdictionConfig(jurId, configId);
        LOGGER.info("Jurisdiction configuration value is deleted..");
        return "redirect:/home";
    }

    @RequestMapping(path = "/deleteJurisdiction/{jurId}")
    public String deleteJurisdiction(@PathVariable("jurId") Long jurId) throws NoSuchFieldException {
        jurisdictionService.deleteJurisdiction(jurId);
        return "redirect:/list";
    }

    // Create a model attribute to load it in the view before every request mapping
    @ModelAttribute("jurisdictionList")
    public List<Jurisdiction> jurisdictionCodeList() {
        List<Jurisdiction> jurisdictionList = jurisdictionService.getAllJurisdictions();

        return jurisdictionList;
    }

    @ModelAttribute("configurationList")
    public List<Configuration> configurationValueList() {
        List<Configuration> configurationList = configurationService.getAllConfiguration();

        return configurationList;
    }
}
package ru.tsystems.reha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.entity.Remedy;
import ru.tsystems.reha.entity.RemedyType;
import ru.tsystems.reha.service.RemedyService;
import ru.tsystems.reha.service.ServiceException;

import java.util.List;

@Controller
@RequestMapping("/remedy")
public class RemedyController {

    private static final Logger LOG = Logger.getLogger(RemedyController.class);

    @Autowired
    private RemedyService remedyService;

    @GetMapping("/list")
    public String listRemedies(Model theModel) {
        try {
            List<Remedy> remedies = remedyService.getRemedies();
            theModel.addAttribute("remedies", remedies);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            theModel.addAttribute("exception", e.getError().getMessage());
        }
        return "list-remedies";
    }

    @PostMapping("/saveRemedy")
    public String saveUser(@ModelAttribute("remedy") Remedy remedy) {
        try {
            remedyService.saveRemedy(remedy);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/remedy/list";
    }

    @GetMapping("/updateRemedy")
    public String showFormForUpdate(@RequestParam("remedyId") int theId,
                                    Model theModel) {
        try {
            Remedy remedy = remedyService.getRemedy(theId);
            theModel.addAttribute("remedy", remedy);
            theModel.addAttribute("remedyTypes", RemedyType.values());
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "remedy-form";
    }

    @GetMapping("/addRemedy")
    public String showFormForAdd(Model theModel) {
        Remedy remedy = new Remedy();
        theModel.addAttribute("remedy", remedy);
        theModel.addAttribute("remedyTypes", RemedyType.values());
        return "remedy-form";
    }

    @GetMapping("/delete")
    public String deleteRemedy(@RequestParam("remedyId") int theId) {
        try {
            remedyService.deleteRemedy(theId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/remedy/list";
    }
}

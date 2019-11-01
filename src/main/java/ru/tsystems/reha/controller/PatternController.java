package ru.tsystems.reha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.entity.Pattern;
import ru.tsystems.reha.service.PatternService;
import ru.tsystems.reha.service.ServiceException;

import java.util.List;

@Controller
@RequestMapping("/pattern")
public class PatternController {

    private static final Logger LOG = Logger.getLogger(PatternController.class);

    @Autowired
    private PatternService patternService;

    @GetMapping("/list")
    public String listPatients(Model model) {
        try {
            List<Pattern> patterns = patternService.getPatterns();
            model.addAttribute("pattern", patterns);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        }
        return "list-patterns";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        Pattern pattern = new Pattern();
        model.addAttribute("pattern", pattern);

        return "pattern-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("patternId") int theId, Model theModel) {
        try {
            Pattern pattern = patternService.getPattern(theId);
            theModel.addAttribute("pattern", pattern);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "pattern-form";
    }

    @PostMapping("/savePattern")
    public String savePatient(@ModelAttribute("patient") Pattern pattern) {
        try {
            patternService.savePattern(pattern);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/pattern/list";
    }

    @GetMapping("/delete")
    public String deletePattern(@RequestParam("patternId") int theId) {
        try {
            patternService.deletePattern(theId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/patient/list";
    }

}

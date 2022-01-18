package ua.com.rostylka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.rostylka.dao.FlowCounterDao;
import ua.com.rostylka.models.FlowCounter;

@Controller
@RequestMapping("/flowcounters")
public class FlowcountersController {
    @Autowired
    FlowCounterDao flowCounterDao;
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("flowcounters", flowCounterDao.readAll());
        return "flowcounters/list";
    }
    
    @GetMapping("/{id}")
    public String calc(@PathVariable("id") int id, Model model) {
        model.addAttribute("flowcounter", flowCounterDao.readByID(id));
        return "flowcounters/calc";
    }

    @GetMapping("/new")
    public String newFlowCounter(@ModelAttribute("flowcounter") FlowCounter flowcounter){
        return "flowcounters/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("flowcounter") FlowCounter flowcounter){
        flowCounterDao.create(flowcounter);
        return "redirect: flowcounters/list";
    }        

}

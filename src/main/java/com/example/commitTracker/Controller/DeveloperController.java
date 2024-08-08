package com.example.commitTracker.Controller;

import com.example.commitTracker.Model.Developer;
import com.example.commitTracker.Repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class DeveloperController {
    @Autowired
    private DeveloperRepository developerRepository;

    @GetMapping("/developer/{id}")
    public String getDeveloper(@PathVariable Long id, Model model) {
        Developer developer = developerRepository.findById(id).orElseThrow();
        model.addAttribute("developer", developer);
        return "developer";
    }
}

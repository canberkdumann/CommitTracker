package com.example.commitTracker.Controller;

import com.example.commitTracker.Model.Commit;
import com.example.commitTracker.Repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class CommitDetailController {
    @Autowired
    private CommitRepository commitRepository;

    @GetMapping("/commit/{hash}")
    public String getCommit(@PathVariable String hash, Model model) {
        Commit commit = commitRepository.findById(hash).orElseThrow();
        model.addAttribute("commit", commit);
        return "commitDetail";
    }
}

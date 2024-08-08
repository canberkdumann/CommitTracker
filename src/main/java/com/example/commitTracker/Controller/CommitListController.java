package com.example.commitTracker.Controller;

import com.example.commitTracker.Model.Commit;
import com.example.commitTracker.Model.Developer;
import com.example.commitTracker.Services.CommitService;
import com.example.commitTracker.Services.DeveloperService;
import com.example.commitTracker.Services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
public class CommitListController {
    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private CommitService commitService;

    @GetMapping("/commits")
    public String getCommits(Model model) {
        String owner = "owner_name";
        String repo = "repo_name";
        String since = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ISO_DATE);

        List<Commit> commits = gitHubService.fetchGithubCommits(owner, repo, since);

        for (Commit commit : commits) {
            Developer developer = developerService.findByUsername(commit.getDeveloper().getUsername());
            if (developer == null) {
                developer = developerService.saveDeveloper(commit.getDeveloper());
            }
            commit.setDeveloper(developer);
            commitService.saveCommits(Arrays.asList(commit));
        }

        model.addAttribute("commits", commits);
        return "commitList";
    }
}

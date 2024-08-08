package com.example.commitTracker.Services;

import com.example.commitTracker.Model.Commit;
import com.example.commitTracker.Model.Developer;
import com.example.commitTracker.Repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitService {
    @Autowired
    private CommitRepository commitRepository;

    public List<Commit> saveCommits(List<Commit> commits) {
        return commitRepository.saveAll(commits);
    }

    public List<Commit> findByDeveloper(Developer developer) {
        return commitRepository.findByDeveloper(developer);
    }
}

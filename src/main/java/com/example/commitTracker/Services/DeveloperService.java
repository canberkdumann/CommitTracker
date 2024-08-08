package com.example.commitTracker.Services;

import com.example.commitTracker.Model.Developer;
import com.example.commitTracker.Repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public Developer findByUsername(String username) {
        return developerRepository.findByUsername(username).orElse(null);
    }
}

package com.example.commitTracker.Repository;

import com.example.commitTracker.Model.Commit;
import com.example.commitTracker.Model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitRepository extends JpaRepository<Commit, String> {
    List<Commit> findByDeveloper(Developer developer);
}


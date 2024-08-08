package com.example.commitTracker.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Commit {

    @Id
    String hash;
    LocalDateTime timestamp;
    String message;
    String patch;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    Developer developer;
}

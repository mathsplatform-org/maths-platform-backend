package com.mathsplatform.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    private LocalDateTime joinedAt = LocalDateTime.now();
}

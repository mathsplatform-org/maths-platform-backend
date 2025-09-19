package com.mathsplatform.backend.repository;

import com.mathsplatform.backend.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}

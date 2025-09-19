package com.mathsplatform.backend.repository;

import com.mathsplatform.backend.model.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassStudentRepository extends JpaRepository<ClassStudent, Long> {
}

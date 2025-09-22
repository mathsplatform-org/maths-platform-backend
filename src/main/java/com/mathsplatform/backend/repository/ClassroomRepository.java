package com.mathsplatform.backend.repository;

import com.mathsplatform.backend.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByInviteCode(String inviteCode);
    List<Classroom> findByTeacherId(Long teacherId);
}
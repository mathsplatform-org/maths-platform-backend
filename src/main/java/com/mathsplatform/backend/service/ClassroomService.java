package com.mathsplatform.backend.service;

import com.mathsplatform.backend.dto.ClassroomDTO;
import com.mathsplatform.backend.model.Classroom;
import com.mathsplatform.backend.model.User;
import com.mathsplatform.backend.repository.ClassroomRepository;
import com.mathsplatform.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;

    public ClassroomService(ClassroomRepository classroomRepository, UserRepository userRepository) {
        this.classroomRepository = classroomRepository;
        this.userRepository = userRepository;
    }

    // Convert Entity -> DTO
    private ClassroomDTO toDTO(Classroom classroom) {
        return ClassroomDTO.builder()
                .id(classroom.getId())
                .name(classroom.getName())
                .inviteCode(classroom.getInviteCode())
                .teacherId(classroom.getTeacher().getId())
                .build();
    }

    // Convert DTO -> Entity
    private Classroom toEntity(ClassroomDTO dto) {
        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + dto.getTeacherId()));

        return Classroom.builder()
                .id(dto.getId())
                .name(dto.getName())
                .inviteCode(dto.getInviteCode())
                .teacher(teacher)
                .build();
    }

    public ClassroomDTO createClassroom(ClassroomDTO dto) {
        Classroom classroom = toEntity(dto);
        return toDTO(classroomRepository.save(classroom));
    }

    public List<ClassroomDTO> getAllClassrooms() {
        return classroomRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClassroomDTO> getClassroomById(Long id) {
        return classroomRepository.findById(id).map(this::toDTO);
    }

    public Optional<ClassroomDTO> getClassroomByInviteCode(String inviteCode) {
        return classroomRepository.findByInviteCode(inviteCode).map(this::toDTO);
    }

    public List<ClassroomDTO> getClassroomsByTeacher(Long teacherId) {
        return classroomRepository.findByTeacherId(teacherId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClassroomDTO> updateClassroom(Long id, ClassroomDTO dto) {
        return classroomRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setInviteCode(dto.getInviteCode());

            User teacher = userRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + dto.getTeacherId()));
            existing.setTeacher(teacher);

            return toDTO(classroomRepository.save(existing));
        });
    }

    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}


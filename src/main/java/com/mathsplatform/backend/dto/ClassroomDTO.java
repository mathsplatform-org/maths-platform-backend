package com.mathsplatform.backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDTO {
    private Long id;
    private String name;
    private String inviteCode;
    private Long teacherId;  // store teacher’s ID, not the whole object
}


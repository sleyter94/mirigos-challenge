package com.mirigos.challenge.dto;

import com.mirigos.challenge.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDto {

    private Long id;
    private String lastname;
    private String firstname;

    public Student toEntity(){
        return Student.builder()
                .lastname(lastname)
                .firstname(firstname)
                .id(id)
                .build();
    }
}

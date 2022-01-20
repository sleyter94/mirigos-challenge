package com.mirigos.challenge.model;

import com.mirigos.challenge.dto.StudentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class Student {

    private Long id;
    private String lastname;
    private String firstname;
    private Set<Course> courseSet;

    public StudentDto toDto(){
        return StudentDto.builder()
                .firstname(firstname)
                .lastname(lastname)
                .id(id)
                .build();
    }
}

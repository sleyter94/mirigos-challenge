package com.mirigos.challenge.model;

import com.mirigos.challenge.dto.CourseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class Course {

    private String code;
    private String title;
    private String description;
    private Set<Student> studentSet;

    public CourseDto toDto(){
        return CourseDto.builder()
                .code(code)
                .title(title)
                .description(description)
                .build();
    }
}

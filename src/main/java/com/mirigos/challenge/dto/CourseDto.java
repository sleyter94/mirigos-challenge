package com.mirigos.challenge.dto;

import com.mirigos.challenge.model.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseDto {

    private String code;
    private String title;
    private String description;

    public Course toEntity(){
        return Course.builder()
                .title(title)
                .code(code)
                .description(description)
                .build();
    }
}

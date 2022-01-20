package com.mirigos.challenge.service;

import com.mirigos.challenge.dto.CourseDto;
import com.mirigos.challenge.model.Course;
import com.mirigos.challenge.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private DataRepository dataRepository;

    public CourseService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<CourseDto> list(String search){
        return Optional.ofNullable(search)
            .map(s -> dataRepository.courses.stream()
                .filter(course ->
                        course.getCode().contains(search)
                        || course.getTitle().contains(search)
                        || course.getDescription().contains(search)
                )
                .map(Course::toDto)
                .collect(Collectors.toList())
            ).orElse(dataRepository.courses.stream().map(Course::toDto).collect(Collectors.toList()));
    }

    public List<CourseDto> listByStudent(Long studentId) {
        return dataRepository.courses.stream()
                .filter(course ->
                        course.getStudentSet().stream().anyMatch(student -> student.getId().equals(studentId)))
                .map(Course::toDto)
                .collect(Collectors.toList());
    }

    public CourseDto create(CourseDto course){
        dataRepository.courses.add(course.toEntity());
        return course;
    }

    public CourseDto update(CourseDto course){
        dataRepository.courses.stream()
                .filter(cou -> cou.getCode().equals(course.getCode()))
                .map(cou -> {
                    cou.setCode(course.getCode());
                    cou.setDescription(course.getDescription());
                    cou.setTitle(course.getTitle());
                    return cou;
                }).findFirst().orElseThrow();
        return course;
    }

    public void delete(String courseCode){
        dataRepository.students.forEach(student -> student.getCourseSet().removeIf(course -> course.getCode().equals(courseCode)));
        dataRepository.courses.removeIf(course -> course.getCode().equals(courseCode));
    }
}

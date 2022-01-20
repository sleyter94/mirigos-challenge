package com.mirigos.challenge.service;

import com.mirigos.challenge.dto.StudentDto;
import com.mirigos.challenge.model.Course;
import com.mirigos.challenge.model.Student;
import com.mirigos.challenge.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private DataRepository dataRepository;

    public StudentService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<StudentDto> list(String search){
        return Optional.ofNullable(search)
                .map(s -> dataRepository.students.stream().filter(
                        student -> student.getId().toString().contains(s)
                        || student.getFirstname().contains(s)
                        || student.getLastname().contains(s)
                    ).map(Student::toDto)
                    .collect(Collectors.toList())
                ).orElse(dataRepository.students.stream().map(Student::toDto).collect(Collectors.toList()));
    }

    public List<StudentDto> listByCourse(String courseCode){
        return dataRepository.students.stream()
                .filter(student ->
                        student.getCourseSet().stream().anyMatch(course -> course.getCode().equals(courseCode)))
                .map(Student::toDto)
                .collect(Collectors.toList());
    }

    public StudentDto create(StudentDto student){
        dataRepository.students.add(student.toEntity());
        return student;
    }

    public StudentDto update(StudentDto student) throws Exception {
        dataRepository.students.stream()
                .filter(stu -> stu.getId().equals(student.getId()))
                .map(stu -> {
                    stu.setFirstname(student.getFirstname());
                    stu.setLastname(student.getLastname());
                    stu.setId(student.getId());
                    return stu;
                }).findFirst()
                .orElseThrow(()-> new Exception("Business Error"));
        return student;
    }

    public void delete(Long studentId){
        dataRepository.courses.forEach(course -> course.getStudentSet().removeIf(student -> student.getId().equals(studentId)));
        dataRepository.students.removeIf(student -> student.getId().equals(studentId));
    }

}

package com.mirigos.challenge.controller;

import com.mirigos.challenge.dto.StudentDto;
import com.mirigos.challenge.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDto> listStudents(@RequestParam(required = false) String search){
        return studentService.list(search);
    }

    @GetMapping("/students/courses/{courseCode}")
    public List<StudentDto> listStudentsByCourse(@PathVariable String courseCode){
        return studentService.listByCourse(courseCode);
    }

    @PostMapping("/students")
    public StudentDto addStudent(@RequestBody StudentDto student){
        return studentService.create(student);
    }

    @PutMapping("/students/{studentId}")
    public StudentDto updateStudent(@PathVariable Long studentId, @RequestBody StudentDto student) throws Exception {
        return studentService.update(student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.delete(studentId);
    }
}

package com.mirigos.challenge.controller;

import com.mirigos.challenge.dto.CourseDto;
import com.mirigos.challenge.service.CourseService;
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
public class CourseController {


    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<CourseDto> listCourses(@RequestParam(required = false) String search){
        return courseService.list(search);
    }

    @GetMapping("/courses/students/{studentId}")
    public List<CourseDto> listCoursesByStudent(@PathVariable Long studentId){
        return courseService.listByStudent(studentId);
    }

    @PostMapping("/courses")
    public CourseDto addCourses(@RequestBody CourseDto course){
        return courseService.create(course);
    }

    @PutMapping("/courses/{courseCode}")
    public CourseDto updateCourses(@PathVariable String courseCode, @RequestBody CourseDto course){
        return courseService.update(course);
    }

    @DeleteMapping("/courses/{courseCode}")
    public void deleteCourses(@PathVariable String courseCode){
        courseService.delete(courseCode);
    }



}

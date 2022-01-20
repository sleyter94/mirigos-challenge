package com.mirigos.challenge.repository;

import com.mirigos.challenge.model.Student;
import com.mirigos.challenge.model.Course;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Repository
public class DataRepository {

    public List<Student> students;
    public List<Course> courses;

    @PostConstruct
    public void initializeData(){
        Course course1 = Course.builder()
                .code("101")
                .title("Introduction to programming")
                .description("programming")
                .studentSet(new HashSet<>())
                .build();
        Course course2 = Course.builder()
                .code("102")
                .title("Algorithms")
                .description("programming")
                .studentSet(new HashSet<>())
                .build();
        Course course3 = Course.builder()
                .code("103")
                .title("Data Structures")
                .description("programming")
                .studentSet(new HashSet<>())
                .build();

        Student student1 = Student.builder()
                .id(123L)
                .firstname("Sleyter")
                .lastname("Sandoval")
                .courseSet(new HashSet<>())
                .build();

        Student student2 = Student.builder()
                .id(456L)
                .firstname("Glenn")
                .lastname("Jayo")
                .courseSet(new HashSet<>())
                .build();

        Student student3 = Student.builder()
                .id(789L)
                .firstname("Juan")
                .lastname("Perez")
                .courseSet(new HashSet<>())
                .build();

        course1.getStudentSet().add(student1);
        student1.getCourseSet().add(course1);
        course2.getStudentSet().add(student2);
        student2.getCourseSet().add(course2);
        course3.getStudentSet().add(student3);
        student3.getCourseSet().add(course3);
        course3.getStudentSet().add(student2);
        student2.getCourseSet().add(course3);

        students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
    }

}

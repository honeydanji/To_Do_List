package com.todoApp.todoproject.controller;


import com.todoApp.todoproject.entity.Course;
import com.todoApp.todoproject.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 나는 기본 주소를 사용하겠다. = { "http://localhost:8080"}
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController // 해줘야 Mapping이 가능하다.
public class CourseController {

    @Autowired
    private CourseService courseService;
    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    //POSt
    @PostMapping("/addCourse") // 데이터 1개 넣기
    public Course addCourse(@RequestBody Course course){
        logger.info("Course object {}", course.toString());
        return courseService.saveCourse(course);
        // course라는 데이터가 들어 있는 객체를 저장한다.
        // 데이터를 넣어줘야 DB에 데이터가 존재하겠지?
        // 그래서 PostMapping을 사용하는 것이다.
    }

    @PostMapping("/addCourses") // 데이터 여러개 넣기 = List
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return courseService.saveCourses(courses);
    }

    //GET
    @GetMapping("/courses") // 모든 데이터 가져오기
    public List<Course> getAllCourses() {
        return courseService.getCourses();
    }
    @GetMapping("/courseById/{id}") // 해당 id 데이터가져오기
    public Course findCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
    @GetMapping("/courseByName/{name}") // 해당 name 데이터 가져오기
    public Course findCourseByName(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }
    @GetMapping("/listCourseByUsername/{username}") // 해당 username 데이터 가져오기
    public List<Course> findCoursesByUsername(@PathVariable String username) {
        return courseService.getCoursesForUser(username);
    }

    //PUT
    @PutMapping("/update") // 데이터 수정하기
    public Course updateCourse(@RequestBody Course course)
    {
        System.out.println("UPDATED");
        return courseService.updateCourse(course);
    }


    //DELETE
    @DeleteMapping("/delete/{id}") // id에 해당하는 데이터 삭제하기
    public String deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }


}

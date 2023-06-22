package com.todoApp.todoproject.repositories;

import com.todoApp.todoproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByName(String name);

    List<Course> findAllByUsername(String username); // CRUD 중에서 GET을 수행한다.

}

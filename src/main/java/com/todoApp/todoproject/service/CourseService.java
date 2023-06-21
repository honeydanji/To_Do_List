package com.todoApp.todoproject.service;

import com.todoApp.todoproject.entity.Course;
import com.todoApp.todoproject.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    //생성자 종속성 주입했기에 따로 객체를 만들지 않아도 된다.

    //POST
    public Course saveCourse(Course course){
        System.out.println(course.toString());
        return courseRepository.save(course);
    }

    //Optional
    public List<Course> saveCourses(List<Course> courses){
        return courseRepository.saveAll(courses);
    }

    //GET
    public List<Course> getCourses(){
        return courseRepository.findAll();
        // findAll()를 통해서 모든 데이터 가져온다.
    }
    public Course getCourseById(int id){
        return courseRepository.findById(id).orElse(null);
        // findById(id)를 통해 해당 id가 있으면 반환하고 그게 아니면
        // orElse(null)를 통해 null을 반환한다.
    }
    public Course getCourseByName(String name){
        return courseRepository.findByName(name);
        // findByName(name)를 통해 해당 name이 있으면 반환한다. 없으면 null
    }
    public List<Course> getCoursesForUser(String username){
        return courseRepository.findAllByUsername(username);
        // findAllByUsername(username)를 통해 username과 관련된 모든 데이터를
        // List타입으로 가져온다. 즉 레코드 한줄 다 가져오는 거?
    }

    //PUT
    public Course updateCourse(Course course){
        System.out.println("updates");
        Course existing_course = courseRepository.findById(course.getId()).orElse(null);
        // existing_course에 id를 저장한다. id는 고유하기 때문에 데이터 전체가 아닌 id만 저장해도
        // 데이터를 가져 올 수 있다. 암튼 그런 느낌이다. 나머지 데이터는 중복이 존재한다.
        existing_course.setName(course.getName());
        existing_course.setDescription((course.getDescription()));
        existing_course.setUsername((course.getUsername()));
        return courseRepository.save(existing_course);
        // save() : 업데이트 및 삽입 작업을 모두 처리한다.
        //          레코드가 존재하면 업데이트를 하고, 존재하지 않으면 새로운 레코드를 삽입한다.

        // course = update 하려는 데이터가 들어있는 놈
        // 1. id가 존재하는 지 확인한다.(기존 데이터 검색)
        // 2. id가 존재하면 id를 제외한 모든 데이터를 update 하고, 없으면 새로운 레코드를 추가한다.
        // 3. id에 해당 하는 name, description, username을 차례대로 값을 set해준다.
        // 4. existing_course에 수정 된 데이터가 저장 되었고, save()를 이용해 수정 및 삽입해준다.
    }

    //DELETE
    public String deleteCourse(int id){
        courseRepository.deleteById(id);
        // 해당하는 id를 지운다. 해당하는 레코드가 지워진다.
        return id + " id -> course removed/completed";
    }

}

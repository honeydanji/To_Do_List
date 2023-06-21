package com.todoApp.todoproject.service;

import com.todoApp.todoproject.entity.Users;
import com.todoApp.todoproject.repositories.UserRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Users getUser(Users user){
        System.out.println("Service GET *****");
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public boolean getUserByUsername(String username, String password){
        boolean username_present;
        boolean password_present;
        try{
            username_present = userRepository.findByUsername(username) != null ? true : false;
            // username이 존재하면 true
            System.out.println("Username present: " + username_present);

            password_present = userRepository.findTopByPassword(password) != null ? true : false;
            // password가 존재하면 true
            System.out.println("Password present: " + password_present);
        } catch(NonUniqueResultException nre){
            return true;
        }
        return username_present && password_present;
        // username 과 password가 둘 다 일치하면 true == 로그인 성공!
        // false가 나오면 현재 DB에 정보가 없다는 소리다. 그럼? 회원가입을 해야하지만
        // 회원가입은 구현하지 않는다.
    }

    public boolean findUserByUsername(String username){
        boolean username_present;
        try{
            username_present = userRepository.findTopByPassword(username) != null ? true : false;
            // findTopByPassword(username) : username과 password가 동일 레코드 즉 한쌍이 맞으면 true 출력
            System.out.println("Username present (U): " + username_present);
        } catch(NonUniqueResultException nre) {
            return true;
        }
        return username_present;
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }// 사용자 추가 메소드
}

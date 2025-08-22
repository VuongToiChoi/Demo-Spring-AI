package com.demoAI.spring_ai_.service;

import com.demoAI.spring_ai_.dto.UserRequest;
import com.demoAI.spring_ai_.dto.UserResponse;
import com.demoAI.spring_ai_.model.User;
import com.demoAI.spring_ai_.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest){
        User user = User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .gmail(userRequest.getGmail())
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .gmail(userRequest.getGmail())
                .build();


    }
    @Cacheable(value = "allUsers")
    public List<User> getAllUSer(){
        System.out.println("Query DB...");
        return userRepository.findAll();
    }
}

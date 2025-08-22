package com.demoAI.spring_ai_.Controller;

import com.demoAI.spring_ai_.dto.UserRequest;
import com.demoAI.spring_ai_.dto.UserResponse;
import com.demoAI.spring_ai_.model.User;
import com.demoAI.spring_ai_.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @PostMapping()
    @CacheEvict(value = "allUsers", allEntries = true)
    public UserResponse createUser(@RequestBody UserRequest  userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUSer();
    }
}

package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userservice;

    @GetMapping
    public List<UserResponse> getallusers() {
        return userservice.fetchuser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getuser(@PathVariable long id) {
        Optional<UserResponse> user = userservice.fetchuser(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping
    public String createuser(@RequestBody UserRequest userRequest) {

        userservice.adduser(userRequest);
        return "Successfully added";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateuser (@PathVariable long id, @RequestBody UserRequest userRequest) {
        boolean result = userservice.updateuser(id, userRequest);
        if (result) {
            return ResponseEntity.ok("User added successfully");
        }
        return ResponseEntity.notFound().build();
    }

}

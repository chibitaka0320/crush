package com.example.crush.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.crush.domain.User;
import com.example.crush.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userService.findById(id);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Integer id, @Validated @RequestBody User user) {
        user.setId(id);
        userService.update(user);
    }

    @PostMapping("")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody User user) {
        Integer userId = userService.insert(user);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(userId)
            .toUri();
        ResponseEntity<Void> result = ResponseEntity.created(location).build();

        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsesr(@PathVariable Integer id) {
        userService.delete(id);
    }

}

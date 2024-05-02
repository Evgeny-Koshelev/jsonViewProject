package org.example.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.entities.Orders;
import org.example.entities.Users;
import org.example.services.UserService;
import org.example.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mvc")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @JsonView(Views.UserSummary.class)
    public ResponseEntity<List<Users>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> get(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Users());
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Users> delete(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Users());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Users> create(@RequestBody Users users) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(users));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Users());
        }
    }

    @PatchMapping("/change")
    public ResponseEntity<Users> change(@RequestBody Users users) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(users));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Users());
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Orders>> getAllOrders(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getOrds(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

}

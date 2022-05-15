package app.eventful.web.controller;

import app.eventful.model.User;
import app.eventful.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PatchMapping("/edit")
    public User editUser(@Valid @RequestBody User editedUser) {
        return userService.editUser(editedUser);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}

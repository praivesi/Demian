package com.tutorial.Demian.controller;

import com.tutorial.Demian.model.Board;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
class UserApiController {
    @Autowired
    private UserRepository repository;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    List<User> all() {
        List<User> users = repository.findAll();

        log.debug("Before calling users.get(0).getBoards().size()");
        log.debug("users.get(0).getBoards().size() : {}", users.get(0).getBoards().size());
        log.debug("After calling users.get(0).getBoards().size()");

        return users;
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
//                    User.setTitle(newUser.getTitle());
//                    User.setContent(newUser.getContent());

//                    user.setBoards(newUser.getBoards());
                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());

                    for(Board board : user.getBoards()){
                        board.setUser(user);
                    }

                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

package com.dharaneesh.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveUsers() {
        return service.getAllUsers();
    }

    @GetMapping(path = "/user/{Id}")
    public EntityModel<User> retrieveUser(@PathVariable int Id) {
        User user = service.getUserById(Id);

//       if(user == null)
//          return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);

        if(user==null)
            throw new UserNotFoundException("id:"+Id);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
        entityModel.add(link.withRel("All-Users"));

        return entityModel;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> AddUser(@Valid @RequestBody User user) {
        User savedUser = service.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{Id}")
    public User DeleteUser(@PathVariable int Id) {
        return service.deleteUserById(Id);
    }
}

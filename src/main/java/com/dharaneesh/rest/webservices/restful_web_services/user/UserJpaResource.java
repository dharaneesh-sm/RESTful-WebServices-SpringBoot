package com.dharaneesh.rest.webservices.restful_web_services.user;

import com.dharaneesh.rest.webservices.restful_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserRepository repository;

    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "/jpa/user/{Id}")
    public EntityModel<User> retrieveUser(@PathVariable int Id) {
        Optional<User> user = repository.findById(Id);

//       if(user == null)
//          return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+Id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
        entityModel.add(link.withRel("All-Users"));

        return entityModel;
    }

    @PostMapping(path = "/jpa/user")
    public ResponseEntity<User> AddUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/user/{Id}")
    public void DeleteUser(@PathVariable int Id) {
        repository.deleteById(Id);
    }
}

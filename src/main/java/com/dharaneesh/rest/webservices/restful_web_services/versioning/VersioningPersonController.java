package com.dharaneesh.rest.webservices.restful_web_services.versioning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/person/v1")
    public PersonV1 getFirstVersionPerson() {
        return new PersonV1("Dharaneesh");
    }

    @GetMapping("/person/v2")
    public PersonV2 getSecondVersionPerson() {
        return new PersonV2(new Name("Tony","Stark"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionPersonRequestParams() {
        return new PersonV1("Dharaneesh");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionPersonRequestParams() {
        return new PersonV2(new Name("Tony","Stark"));
    }

    @GetMapping(path = "/person/header", headers = "I-API-Version=1")
    public PersonV1 getFirstVersionPersonHeader1() {
        return new PersonV1("Dharaneesh");
    }

    @GetMapping(path = "/person/header", headers = "I-API-Version=2")
    public PersonV2 getFirstVersionPersonHeader2() {
        return new PersonV2(new Name("Tony","Stark"));
    }

    @GetMapping(path = "/person", produces = "application/v1+json")
    public PersonV1 getFirstVersionPersonMediaType1() {
        return new PersonV1("Dharaneesh");
    }

    @GetMapping(path = "/person", produces = "application/v2+json")
    public PersonV2 getFirstVersionPersonMediaType2() {
        return new PersonV2(new Name("Tony","Stark"));
    }
}

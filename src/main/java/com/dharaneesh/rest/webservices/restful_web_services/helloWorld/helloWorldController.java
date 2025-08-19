package com.dharaneesh.rest.webservices.restful_web_services.helloWorld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Locale;

@RestController //include -> [@ResponseBody] which means return the object directly as the HTTP JSON response
public class helloWorldController {

    private MessageSource messageSource;

    public helloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    //Way 1
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String HelloWorld1() {
        return "Ohh! Shit, Here we go Again!";
    }

    //Way 2
//    @GetMapping(path = "/hello-world")
//    public String HelloWorld2() {
//        return "Ohh! Shit, Here we go Again!";
//    }

    //Returning Bean Instance
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Say My Name");
    }

    //Path Variable
    @GetMapping(path = "/hello-world/path-variable/{n}")
    public String HelloWorldPathVariable(@PathVariable String n) {
        return "I am " + n;
    }

    @GetMapping(path = "/world")
    public String Internationalization() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",
                null, "Hi, I am Ironman", locale);
    }
}

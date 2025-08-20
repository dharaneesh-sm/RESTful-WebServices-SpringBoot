package com.dharaneesh.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    //Static Filtering
    @GetMapping("/filtering/static")
    public UserBean getUserBeansStatic() {
        return new UserBean("dharaneesh@gmail.com", "Dharaneesh", "123456");
    }

    @GetMapping("/filtering-list/static")
    public List<UserBean> getUserBeansListStatic() {
        return Arrays.asList(new UserBean("dharaneesh@gmail.com", "Dharaneesh", "123456"),
                new UserBean("dharaneesh@gmail.com", "Dharaneesh", "123456"));
    }

    //Dynamic Filtering
    @GetMapping("/filtering/dynamic")
    public MappingJacksonValue getUserBeansDynamic() {
        UserBean userBean = new UserBean("dharaneesh@gmail.com", "Dharaneesh", "123456");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userBean);
        return DynamicFiltering(mappingJacksonValue);

//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email", "userName");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);
//        mappingJacksonValue.setFilters(filters);
//        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list/dynamic")
    public MappingJacksonValue getUserBeansListDynamic() {

        List<UserBean> userBeanList = Arrays.asList(new UserBean("dharaneesh@gmail.com", "Dharaneesh", "123456"),
                new UserBean("dharaneesh@gmail.com", "Dharaneesh", "123456"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userBeanList);
        return DynamicFiltering(mappingJacksonValue);

//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email", "userName");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);
//        mappingJacksonValue.setFilters(filters);
//        return mappingJacksonValue;
    }

    public MappingJacksonValue DynamicFiltering(MappingJacksonValue mappingJacksonValue) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email", "userName");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}

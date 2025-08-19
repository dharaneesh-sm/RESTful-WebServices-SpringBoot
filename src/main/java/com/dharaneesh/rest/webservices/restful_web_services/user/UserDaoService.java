package com.dharaneesh.rest.webservices.restful_web_services.user;

import com.dharaneesh.rest.webservices.restful_web_services.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount,"Stark",LocalDate.now().minusYears(20)));
        users.add(new User(++userCount,"Captain",LocalDate.now().minusYears(20)));
        users.add(new User(++userCount,"Thor",LocalDate.now().minusYears(20)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int Id) {
//        return users.get(Id);
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), Id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User createUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User deleteUserById(int Id) {
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), Id);
        users.remove(predicate);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
}

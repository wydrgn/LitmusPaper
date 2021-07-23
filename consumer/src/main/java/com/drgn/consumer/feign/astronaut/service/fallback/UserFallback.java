package com.drgn.consumer.feign.astronaut.service.fallback;

import com.drgn.common.model.User;
import com.drgn.consumer.feign.astronaut.service.inter.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFallback implements UserService {

    @Override
    public List<User> list(String name) {
        return null;
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public String save(User user) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Boolean update(User user) {
        return null;
    }

}

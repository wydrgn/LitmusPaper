package com.drgn.astronaut.service.inter;

import com.drgn.common.exception.ParamException;
import com.drgn.common.model.User;

import java.util.List;

public interface UserService {

    List<User> list(String name);

    User get(String id);

    String save(User user);

    Boolean delete(String id);

    Boolean update(User user);

    String success(String id);

    String fail(String id) throws ParamException;
}

package com.drgn.consumer.feign.astronaut.service.inter;

import com.drgn.common.model.User;
import com.drgn.consumer.config.FeignConfig;
import com.drgn.consumer.feign.astronaut.service.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "astronaut", path = "/astronaut/user", configuration = FeignConfig.class, fallback = UserFallback.class)
public interface UserService {
    /**
     * todo 待测试
     * 这是需要强调的是使用feign时候@PathVariable一定要用value指明参数，
     * 不然会抛出.IllegalStateException: PathVariable annotation was empty on param 异常
     */
    @GetMapping("/list")
    List<User> list(@RequestParam String name);

    @GetMapping("/{id}")
    User get(@PathVariable(value = "id") String id);

    @PostMapping
    String save(@RequestBody User user);

    @DeleteMapping("/{id}")
    Boolean delete(@PathVariable String id);

    @PutMapping
    Boolean update(@RequestBody User user);

}

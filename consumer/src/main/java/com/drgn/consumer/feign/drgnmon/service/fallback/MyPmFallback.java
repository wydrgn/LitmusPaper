package com.drgn.consumer.feign.drgnmon.service.fallback;

import com.drgn.common.model.MyPm;
import com.drgn.consumer.feign.drgnmon.service.inter.MyPmService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: wydrgn
 * @createDate: 2021-06-23 17:06
 */
@Component
public class MyPmFallback implements MyPmService {
    @Override
    public List<MyPm> list(String name) {
        return null;
    }

    @Override
    public MyPm get(String id) {
        return null;
    }

    @Override
    public String save(MyPm myPm) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Boolean update(MyPm myPm) {
        return null;
    }
}

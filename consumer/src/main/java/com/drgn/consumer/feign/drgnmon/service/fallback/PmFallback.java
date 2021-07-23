package com.drgn.consumer.feign.drgnmon.service.fallback;

import com.drgn.common.model.MyPm;
import com.drgn.common.model.Pm;
import com.drgn.consumer.feign.drgnmon.service.inter.PmService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: wydrgn
 * @createDate: 2021-06-23 17:06
 */
@Component
public class PmFallback implements PmService {

    @Override
    public List<Pm> list(String name) {
        return null;
    }

    @Override
    public Pm get(String id) {
        return null;
    }

    @Override
    public String save(Pm pm) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Boolean update(Pm pm) {
        return null;
    }
}

package com.drgn.drgnmon.service.inter;

import com.drgn.common.exception.ParamException;
import com.drgn.common.model.Pm;
import com.drgn.common.model.User;

import java.util.List;

/**
 * @description: 宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-22 2:49
 */
public interface PmService {
    List<Pm> list(String name);

    Pm get(String id);

    String save(Pm pm);

    Boolean delete(String id);

    Boolean update(Pm pm);

}

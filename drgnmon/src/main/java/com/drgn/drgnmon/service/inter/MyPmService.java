package com.drgn.drgnmon.service.inter;

import com.drgn.common.model.MyPm;

import java.util.List;

/**
 * @description: 我的宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-23 3:47
 */
public interface MyPmService {
    List<MyPm> list(String name);

    MyPm get(String id);

    String save(MyPm pm);

    Boolean delete(String id);

    Boolean update(MyPm pm);
}

package com.drgn.drgnmon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drgn.common.annotations.RedisCashe;
import com.drgn.common.mapper.MyPmMapper;
import com.drgn.common.model.MyPm;
import com.drgn.drgnmon.service.inter.MyPmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 我的宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-23 3:48
 */
@Service
public class MyPmServiceImpl implements MyPmService {
    @Resource
    MyPmMapper myPmMapper;

    @RedisCashe(Type = RedisCashe.HandleType.INSERT)
    @Override
    public List<MyPm> list(String name) {
        QueryWrapper<MyPm> qw = new QueryWrapper<>();
        qw.eq("chinese", name);
        List<MyPm> myPmList = myPmMapper.selectList(qw);
        if (myPmList == null) {
            return new ArrayList<>();
        }
        return myPmList;
    }

    @Override
    public MyPm get(String id) {
        return myPmMapper.selectById(id);
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public String save(MyPm myPm) {
        myPmMapper.insert(myPm);
        return myPm.getId();
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public Boolean delete(String id) {
        return myPmMapper.deleteById(id) > 0;
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public Boolean update(MyPm pm) {
        return myPmMapper.updateById(pm) > 0;
    }
}

package com.drgn.astronaut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drgn.astronaut.service.inter.PmService;
import com.drgn.common.annotations.RedisCashe;
import com.drgn.common.mapper.PmMapper;
import com.drgn.common.model.Pm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-22 3:09
 */
@Service
public class PmServiceImpl implements PmService {

    @Resource
    PmMapper pmMapper;

    @RedisCashe(Type = RedisCashe.HandleType.INSERT)
    @Override
    public List<Pm> list(String name) {
        QueryWrapper<Pm> qw = new QueryWrapper<>();
        qw.eq("chinese", name);
        List<Pm> pmList = pmMapper.selectList(qw);
        if (pmList == null) {
            return new ArrayList<>();
        }
        return pmList;
    }

    @Override
    public Pm get(String id) {
        return pmMapper.selectById(id);
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public String save(Pm pm) {
        pmMapper.insert(pm);
        return pm.getId();
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public Boolean delete(String id) {
        return pmMapper.deleteById(id) > 0;
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public Boolean update(Pm pm) {
        return pmMapper.updateById(pm) > 0;
    }
}

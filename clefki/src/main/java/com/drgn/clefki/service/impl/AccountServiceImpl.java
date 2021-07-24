package com.drgn.clefki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drgn.clefki.service.inter.AccountService;
import com.drgn.common.exception.ParamException;
import com.drgn.common.mapper.AccountMapper;
import com.drgn.common.model.Account;
import com.drgn.common.model.VO.AccountVO;
import com.drgn.common.model.query.AccountQuery;
import com.drgn.common.model.utils.QueryWrapperUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 账号相关
 *
 * @author wydrgn
 * @date 2021/7/24 11:53
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public AccountVO get(String id) throws NotFoundException {
        Account account = accountMapper.selectById(id);
        if (account == null) {
            throw new NotFoundException("找不到对应记录");
        }
        // 部分隐私字段处理不应传出
        AccountVO aVO = new AccountVO();
        BeanUtil.copyProperties(account, aVO, "password");
        return aVO;
    }

    @Override
    public List<AccountVO> list(AccountQuery aq) throws ParamException {
        QueryWrapper<Account> qw = QueryWrapperUtil.getByQuery(aq);
        // TODO: 2021/7/24 如果需要返回VO类的list 为了保证性能和效率 需要考虑写mapper.xml
        return new ArrayList<>();
    }

    @Override
    public String save(AccountVO accountVO) {
        Account a = new Account();
        BeanUtil.copyProperties(accountVO, a);
        // TODO: 2021/7/24 加密 账号 密码

        // 保存成功返回id
        return null;
    }

    @Override
    public boolean update(AccountVO accountVO) {
        Account a = new Account();
        BeanUtil.copyProperties(accountVO, a);
        // TODO: 2021/7/24 加密 账号 密码

        // 保存成功返回true
        return false;
    }

    @Override
    public boolean delete(String id) {

        // TODO: 2021/7/24 重要数据删除?是否需要考虑需要什么验证接口临时获取权限
        return false;
    }
}

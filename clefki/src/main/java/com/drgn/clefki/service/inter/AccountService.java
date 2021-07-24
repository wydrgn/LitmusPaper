package com.drgn.clefki.service.inter;

import com.drgn.common.exception.ParamException;
import com.drgn.common.model.VO.AccountVO;
import com.drgn.common.model.query.AccountQuery;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * 账号相关接口
 */
public interface AccountService {
    AccountVO get(String id) throws NotFoundException;

    List<AccountVO> list(AccountQuery aq) throws ParamException;

    String save(AccountVO accountVO);

    boolean update(AccountVO accountVO);

    boolean delete(String id);
}

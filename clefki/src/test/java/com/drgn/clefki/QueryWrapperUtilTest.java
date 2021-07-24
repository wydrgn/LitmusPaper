package com.drgn.clefki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drgn.common.exception.ParamException;
import com.drgn.common.model.Account;
import com.drgn.common.model.query.AccountQuery;
import com.drgn.common.model.utils.QueryWrapperUtil;
import org.junit.jupiter.api.Test;

/**
 * @author wydrgn
 * @date 2021/7/24 14:28
 */
public class QueryWrapperUtilTest {
    @Test
    void getByQuery() {
        AccountQuery aq = new AccountQuery();
        aq.setId("123");
        aq.setPageIndex("1");
        aq.setPageSize("2");

        try {
            QueryWrapper<Account> qw = QueryWrapperUtil.getByQuery(aq);
            System.out.println(qw);
        } catch (ParamException e) {
            e.printStackTrace();
        }
    }
}

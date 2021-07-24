package com.drgn.common.model.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drgn.common.exception.ParamException;
import com.drgn.common.model.query.BaseQuery;

import java.lang.reflect.Field;

/**
 * @author wydrgn
 * @date 2021/7/24 14:03
 */
public class QueryWrapperUtil {

    public static <T extends BaseQuery, R> QueryWrapper<R> getByQuery(T t) throws ParamException {
        Field[] fields = t.getClass().getFields();
        QueryWrapper<R> qw = new QueryWrapper<>();
        try {
            for (Field field : fields) {
                if (!BeanUtil.isEmpty(field.get(t))) {
                    qw.eq(field.getName(), field.get(t));
                }
            }
        } catch (IllegalAccessException e) {
            throw new ParamException("查询参数转换异常:" + e.getMessage());
        }
        return qw;
    }
}

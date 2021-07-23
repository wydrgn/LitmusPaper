package com.drgn.common.utils;

import com.drgn.common.model.utils.SnowFlake;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description: 获取id工具类
 * @author: wydrgn
 * @createDate: 2021-07-02 20:51
 */
@Component
public class IdUtil {

    public static String getSnowFlakeId() {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        SnowFlake snowFlake = (SnowFlake) applicationContext.getBean("snowFlake");
        return String.valueOf(snowFlake.nextId());
    }
}

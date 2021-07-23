package com.drgn.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCashe {
    HandleType Type();

    public enum HandleType {
        DELETE(),// 删除
        INSERT();// 插入/更新
    }
}

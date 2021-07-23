package com.drgn.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @description: 我的宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-23 3:27
 */
@TableName("drgnmon.my_pm")
public class MyPm {
    @TableId("id")
    private String id;
    @TableField("user_id")
    private String userId;// 用户id
    @TableField("national_id")
    private String nationalId;// 全国图鉴id
    @TableField("flash")
    private boolean flash;// 闪光

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }
}

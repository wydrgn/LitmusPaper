package com.drgn.common.model.DTO;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @description:
 * @author: wydrgn
 * @createDate: 2021-06-25 18:03
 */
public class MyPmDTO {
    private String userId;// 用户id
    private String nationalId;// 全国图鉴id
    private boolean flash;// 闪光

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

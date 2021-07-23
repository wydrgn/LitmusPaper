package com.drgn.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @description: 宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-21 2:36
 */
@TableName("drgnmon.pm")
public class Pm {
    @TableId("id")
    private String id;
    @TableField("national_id")
    private String nationalId;// 全国图鉴id
    @TableField("japanese")
    private String japanese; // 日文
    @TableField("chinese")
    private String chinese;// 中文
    @TableField("english")
    private String english;// 英文
    @TableField("type_one")
    private String typeOne;// 属性1
    @TableField("type_two")
    private String typeTwo;// 属性2


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTypeOne() {
        return typeOne;
    }

    public void setTypeOne(String typeOne) {
        this.typeOne = typeOne;
    }

    public String getTypeTwo() {
        return typeTwo;
    }

    public void setTypeTwo(String typeTwo) {
        this.typeTwo = typeTwo;
    }
}

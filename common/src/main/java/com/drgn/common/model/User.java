package com.drgn.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("astronaut.user")
public class User implements Serializable {
    @TableId("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField("password")
    private String password;

    @TableField("drgnmon_flag")
    private String drgnmonFlag;

    @TableField("clefki_flag")
    private String clefkiFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDrgnmonFlag() {
        return drgnmonFlag;
    }

    public void setDrgnmonFlag(String drgnmonFlag) {
        this.drgnmonFlag = drgnmonFlag;
    }

    public String getClefkiFlag() {
        return clefkiFlag;
    }

    public void setClefkiFlag(String clefkiFlag) {
        this.clefkiFlag = clefkiFlag;
    }
}

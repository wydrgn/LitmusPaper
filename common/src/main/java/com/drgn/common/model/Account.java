package com.drgn.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 账号信息
 *
 * @author wydrgn
 * @date 2021/7/24 11:36
 */
@TableName("clefki.account")
public class Account {
    @TableId("id")
    private String id;
    @TableField("user_id")
    private String userId;// 用户id
    @TableField("community_id")
    private String communityId;// 社区名称id
    @TableField("account_number")
    private String accountNumber;// 账号
    @TableField("password")
    private String password;// 密码

    // TODO: 2021/7/24 其他补充数据


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

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

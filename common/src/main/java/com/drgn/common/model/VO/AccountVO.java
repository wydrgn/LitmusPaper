package com.drgn.common.model.VO;

/**
 * @author wydrgn
 * @date 2021/7/24 12:47
 */
public class AccountVO {
    private String id;
    private String userId;// 用户id
    private String accountNumber;// 账号
    private String password;// 密码

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

package com.drgn.common.model.DTO;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * eureka请求服务DTO
 */
public class EurekaServiceRequestDTO {
    @NotBlank(message = "请求地址不能为空")
    private String action; // 请求地址
    @NotBlank(message = "请求服务名不能为空")
    private String serverName; // 服务名
    private Map<String, String> params; // 参数

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}

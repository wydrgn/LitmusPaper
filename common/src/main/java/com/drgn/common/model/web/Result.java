package com.drgn.common.model.web;

import com.alibaba.fastjson.JSONObject;

public class Result {
    private Boolean success;
    private String msg;
    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result FAIL(Object errorMsg) {
        Result r = new Result();
        r.setSuccess(false);
        r.setMsg(errorMsg instanceof String ? (String) errorMsg : JSONObject.toJSONString(errorMsg));
        return r;
    }

    public static Result SUCCESS(Object data) {
        Result r = new Result();
        r.setSuccess(true);
        r.setData(data);
        return r;
    }
}

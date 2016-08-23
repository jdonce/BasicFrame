package com.djonce.sample.model.bean;

/**
 * 此处返回的样式需根据服务返回定义
 * Created by Administrator on 2016/7/22 0022.
 */
public class BaseResponse<T> {
    private int code;
    private String errorMsg;
    private T object;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

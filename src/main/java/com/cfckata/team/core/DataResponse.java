package com.cfckata.team.core;

public class DataResponse<T> {
    /**返回码*/
    private String retCode;
    private String retMsg;

    private T data;

    public final static String SUCCESS = "0000000";
    private final static String SUCCESS_MSG = "操作成功";

    public final static String FAIL = "0000001";

    @SuppressWarnings("all")
    public static <T> DataResponse<T> succeed(T data) {
        DataResponse<T> defaultResponse = new DataResponse<T>();
        defaultResponse.setRetCode(SUCCESS);
        defaultResponse.setRetMsg(SUCCESS_MSG);
        defaultResponse.setData(data);
        return defaultResponse;
    }

    @SuppressWarnings("all")
    public static <T> DataResponse<T> fail(String retCode,String retMsg) {
        DataResponse<T> defaultResponse = new DataResponse<T>();
        defaultResponse.setRetCode(FAIL);
        defaultResponse.setRetCode(retCode);
        defaultResponse.setRetMsg(retMsg);
        return defaultResponse;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}

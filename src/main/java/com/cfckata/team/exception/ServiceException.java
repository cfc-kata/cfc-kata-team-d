package com.cfckata.team.exception;

public class ServiceException extends RuntimeException {
    private String retCode;
    private String retMsg;

    public ServiceException() {
        super();
    }

    public ServiceException(String retCode,String retMsg) {
        super(retCode+","+retMsg);
        this.retCode=retCode;
        this.retMsg=retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}

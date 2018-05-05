package com.example.administrator.smartpillow.model.login;

public class LoginData {
    /**
     * {
     "body": true,
     "code": "string",
     "msg": "string",
     "success": true
     }
     */
    private String body;
    private String code;
    private String msg;
    private boolean success;
    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return body;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return success;
    }

}

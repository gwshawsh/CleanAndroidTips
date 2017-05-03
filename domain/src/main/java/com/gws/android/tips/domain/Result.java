package com.gws.android.tips.domain;

/**
 * Created by Administrator on 2017/3/13.
 */

public class Result {
    public String code = "";
    public String message = "网络错误";
    public boolean isSucceed = false;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }
}

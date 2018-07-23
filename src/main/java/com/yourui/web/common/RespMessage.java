package com.yourui.web.common;

/**
 * 统一返回数据格式
 * @param <T>
 */
public class RespMessage<T> extends Message<T>{
    private int code;
    private String msg = "数据读取成功!";
    private int count;

    public RespMessage() {
        super();
    }

    public RespMessage(int code, String msg, int count) {
        this.code = code;
        this.msg = msg;
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RespMessage{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                '}';
    }
}

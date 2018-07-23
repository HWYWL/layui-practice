package com.yourui.web.common;

/**
 * 统一请求数据格式
 * @param <T>
 */
public class Message<T> {
    private T data;

    public Message() {
        super();
    }

    public Message(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data=" + data +
                '}';
    }
}

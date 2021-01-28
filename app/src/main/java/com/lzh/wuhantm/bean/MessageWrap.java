package com.lzh.wuhantm.bean;

/**
 * EventBus事件（消息）
 */
public class MessageWrap {
    public final String message;

    public static MessageWrap getInstance(String message){
        return new MessageWrap(message);
    }

    private MessageWrap(String message){
        this.message = message;
    }
}

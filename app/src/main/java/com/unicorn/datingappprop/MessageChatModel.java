package com.unicorn.datingappprop;

public class MessageChatModel {
    private String text;
    private String time;
    private int viewType;
    private int resource;

    public MessageChatModel(String text, String time, int viewType, int resource) {
        this.text = text;
        this.time = time;
        this.viewType = viewType;
        this.resource = resource;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public int getResource() {
        return resource;
    }

    public int getViewType() {
        return viewType;
    }
}
package com.example.demo_course_selection.dto;

public class Message {
    private boolean isMassMsg; //是否是群发
    private String from; //发送者
    private String to; //接收者

    private String text; //文本信息

    public Message(){}

    public Message(boolean isMassMsg, String from, String to, String text) {
        this.isMassMsg = isMassMsg;
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public boolean isMassMsg() {
        return isMassMsg;
    }

    public void setMassMsg(boolean massMsg) {
        isMassMsg = massMsg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "isMassMsg=" + isMassMsg +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

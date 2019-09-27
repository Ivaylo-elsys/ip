package org.elsys.ip.websocket;

public class Message {

    private String from;
    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }
}

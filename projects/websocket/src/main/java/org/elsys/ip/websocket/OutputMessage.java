package org.elsys.ip.websocket;

public class OutputMessage extends Message {

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OutputMessage(String from, String text, String time) {
        super(from, text);
        this.time = time;
    }
}

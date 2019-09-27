package org.elsys.ip.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebsocketController {
    Map<String, Date> lastMessageMap = new HashMap<>();

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        Date now = new Date();
        String time = new SimpleDateFormat("HH:mm").format(now);
        if (lastMessageMap.containsKey(message.getFrom()) &&
        now.getTime() - lastMessageMap.get(message.getFrom()).getTime() < 3000) {
            return new OutputMessage(message.getFrom(), "User " + message.getFrom() + " is banned", time);
        }

        lastMessageMap.put(message.getFrom(), now);
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}

package conway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
public class CommandSender {

    private WebSocketSession session;

    @PostConstruct
    public void init() {
        try {
            StandardWebSocketClient client = new StandardWebSocketClient();
            session = client.doHandshake(new MyWebSocketHandler(), new URI("ws://localhost:8080/wsupdates")).get();
            sendCommand("start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        try {
            session.sendMessage(new TextMessage(command));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
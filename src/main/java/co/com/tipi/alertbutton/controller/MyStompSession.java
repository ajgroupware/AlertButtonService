package co.com.tipi.alertbutton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyStompSession {

    private static final Logger logger = LoggerFactory.getLogger(MyStompSession.class);
    private static MyStompSession instance;
    private StompSession session;

    private MyStompSession() {
        logger.debug("--MyStompSession");
        // init code goes here
        try {
            WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
            List<Transport> transports = new ArrayList<>(1);
            transports.add(new WebSocketTransport(simpleWebSocketClient));
            SockJsClient sockJsClient = new SockJsClient(transports);

            WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            //String url = "ws://localhost:8080/gs-guide-websocket";
            String url = "/gs-guide-websocket";
            StompSessionHandler sessionHandler = new MyStompSessionHandler();
            StompSession session = stompClient.connect(url, sessionHandler).get();
            String topic = "/topic";
            session.subscribe(topic, new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return null;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    System.err.println(payload.toString());
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyStompSession getInstance() {
        if (instance == null) {
            instance = new MyStompSession();
        }

        return instance;
    }

    private class MyStompSessionHandler extends StompSessionHandlerAdapter {

    }

    public StompSession getSession() {
        return session;
    }
}

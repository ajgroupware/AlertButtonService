package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.AlertButton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WebSocketClient {


    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    private static WebSocketClient INSTANCE;

    public static WebSocketClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebSocketClient();

            ListenableFuture<StompSession> f = INSTANCE.connect();
            //StompSession stompSession = null;
            try {
                myStompSession = f.get();

                logger.info("--Subscribing to alert topic using session " + myStompSession);
                INSTANCE.subscribeAlertMessage(myStompSession);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        return INSTANCE;
    }

    public ListenableFuture<StompSession> connect() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        String url = "ws://{host}:{port}/gs-guide-websocket";
        //return stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 8090);
    }

    public void subscribeAlertMessage(StompSession stompSession) throws ExecutionException, InterruptedException {
        stompSession.subscribe("/topic/alert", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("--Received Message " + new String((byte[]) o), StandardCharsets.UTF_8);
            }
        });
    }

    public void sendAlert(StompSession stompSession, AlertButton alertButton) {
        logger.info("--sendAlert ");
        ObjectMapper mapper = new ObjectMapper();
        //String jsonAlert = "{ \"message\" : \"" + msg + "\" }";
        String jsonAlert = null;
        try {
            jsonAlert = mapper.writeValueAsString(alertButton);
            stompSession.send("/app/alert", jsonAlert.getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public static StompSession myStompSession;

    public void sendAlert(AlertButton alertButton) {
        logger.info("--sendAlert ");
        ObjectMapper mapper = new ObjectMapper();
        //String jsonAlert = "{ \"message\" : \"" + msg + "\" }";
        String jsonAlert = null;
        try {
            jsonAlert = mapper.writeValueAsString(alertButton);
            myStompSession.send("/app/alert", jsonAlert.getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            logger.info("--Now connected");
        }
    }

}

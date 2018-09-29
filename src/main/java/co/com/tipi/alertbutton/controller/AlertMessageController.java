package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.Alert;
import co.com.tipi.alertbutton.domain.AlertButton;
import co.com.tipi.alertbutton.service.AlertService;
import co.com.tipi.alertbutton.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;

import javax.validation.Valid;

@Component
//@Controller
public class AlertMessageController {
    private static final Logger logger = LoggerFactory.getLogger(AlertMessageController.class);

    @Autowired
    private AlertService alertService;

    @Autowired
    private ParameterService parameterService;

    public void addMessage(AlertButton alertButton) {
        logger.info("--addMessage");

        try {
            //AlertButton button = parameterService.getButtonByPhone(alertButton.getPhone());
            Alert alert = new Alert();
            //alert.setButton(button);
            alert.setButton(alertButton);
            alertService.addAlert(alert);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            /*
            WebSocketClient webSocketClient = new WebSocketClient();

            ListenableFuture<StompSession> f = webSocketClient.connect();
            StompSession stompSession = f.get();

            logger.info("--Subscribing to alert topic using session " + stompSession);
            webSocketClient.subscribeAlertMessage(stompSession);

            logger.info("--Sending alert message " + stompSession);

            webSocketClient.sendAlert(stompSession, alertButton);
            */
            WebSocketClient webSocketClient = WebSocketClient.getInstance();
            webSocketClient.sendAlert(alertButton);

            Thread.sleep(600);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("--addMessage created: " + alertButton);
    }
}

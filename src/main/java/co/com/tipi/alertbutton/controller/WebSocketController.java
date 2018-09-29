package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.Alert;
import co.com.tipi.alertbutton.domain.AlertButton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /*
    @MessageMapping("/alert")
    @SendTo("/topic/alert")
    public Alert addMessage(AlertButton alertButton) throws Exception {
        logger.info("--addMessage");
        //Thread.sleep(600); // simulated delay
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Alert alert = new Alert();
        alert.setDate(dateFormat.format(new Date()));
        alert.setButton(alertButton);

        return alert;
        //return new Message("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
    }
    */


    @MessageMapping("/alert")
    @SendTo("/topic/alert")
    public String addMessage(AlertButton alertButton) throws Exception {
        logger.info("--addMessage");
        //Thread.sleep(600); // simulated delay
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Alert alert = new Alert();
        alert.setDate(dateFormat.format(new Date()));
        alert.setButton(alertButton);

        ObjectMapper mapper = new ObjectMapper();
        String jsonAlert = null;
        try {
            final byte[] data = mapper.writeValueAsBytes(alert);
            jsonAlert = new String(data, "UTF-8");
            //jsonAlert = mapper.writeValueAsString(alert);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonAlert;
        //return new Message("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
    }

}

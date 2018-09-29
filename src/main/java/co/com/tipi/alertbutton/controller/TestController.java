package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.AlertButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;


@Controller
public class TestController implements TestControllerApi{

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private AlertMessageController alertMessage;


    @org.springframework.beans.factory.annotation.Autowired
    public TestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        logger.info("--TestController");
    }

    @Override
    public ResponseEntity<Void> addMessage(@Valid @RequestBody AlertButton body) {
        logger.info("--addMessage " + body);
        String accept = request.getHeader("Accept");
        alertMessage.addMessage(body);

        logger.info("--addMessage created: " + body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}

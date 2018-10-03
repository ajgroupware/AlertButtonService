package co.com.tipi.alertbutton.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.com.tipi.alertbutton.domain.AlertButton;
import co.com.tipi.alertbutton.service.AlertService;
import co.com.tipi.alertbutton.sip.SipLiveEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    AlertService alertService;

    @Autowired
    private AlertMessageController alertMessage;

    //@Scheduled(fixedRate = 4000)
    //@Scheduled(fixedDelay = 500)
    public void reportCurrentTime() {
        log.info("--The time is now {} ", dateFormat.format(new Date()));

        //findCalls();
    }

    /*
    private void findCalls() {
        List<String> list = alertService.listCall();
        log.info("--listPhone: " + list.toString());
        for (String phone : list) {
            log.info("--Phone: " + phone);
            AlertButton alertButton = alertService.findActiveButtonByPhone(phone);
            if (alertButton != null) { // Verificar si el teléfono existe en la base de datos
                logger.info("--addMessage " );
                alertMessage.addMessage(alertButton);
            }
        }
    }
    */
}

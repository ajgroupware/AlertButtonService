package co.com.tipi.alertbutton.sip;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.tipi.alertbutton.controller.AlertMessageController;
import co.com.tipi.alertbutton.domain.AlertButton;
import co.com.tipi.alertbutton.service.AlertService;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
//@Component
public class SipLiveEvents implements ManagerEventListener{
    private static final Logger logger = LoggerFactory.getLogger(SipLiveEvents.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    AlertService alertService;

    @Autowired
    private AlertMessageController alertMessage;

    private ManagerConnection managerConnection;

    public SipLiveEvents() throws IOException {
        logger.info("--SipLiveEvents {0} ", dateFormat.format(new Date()));
        ManagerConnectionFactory factory = new ManagerConnectionFactory("localhost", 5038, "admin", "Pw.123456");

        this.managerConnection = factory.createManagerConnection();

    }

    //@PostConstruct
    @Scheduled(fixedDelay = 500)
    public void init() {

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //@PostConstruct
    //@Scheduled(initialDelay = 500)
    public void run() throws IOException, AuthenticationFailedException,
            TimeoutException, InterruptedException {
        logger.info("--run {0} ", dateFormat.format(new Date()));
        // register for events
        managerConnection.addEventListener(this);

        // connect to Asterisk and log in
        managerConnection.login();

        // request channel state
        managerConnection.sendAction(new StatusAction());

        // wait 10 min for events to come in
        //Thread.sleep(1000*60*10);
        boolean active = true;
        while (active) {
            // wait 10 min for events to come in
            //Thread.sleep(1000*60*10);
            try {
                Thread.sleep(1000*60*10);
            } catch (InterruptedException e) {
                try {
                    Thread.currentThread().interrupt(); // restore interrupted status
                } catch (Exception ex) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // and finally log off and disconnect
        managerConnection.logoff();
    }

    @Override
    public void onManagerEvent(ManagerEvent event) {
        if (event.getCallerIdNum() != null && event.getCallerIdNum().length() > 0) {
        //if (event.getCallerIdNum() != null && event.getCallerIdNum().length() > 0 && event.getChannelStateDesc() != null && "Ringing".equals(event.getChannelStateDesc())) {
            System.out.println("--onManagerEvent");
            String phone = event.getCallerIdNum();
            System.out.println("--CallerIdNum: " + phone);
            System.out.println("--CallerIdName: " + event.getCallerIdName());
            System.out.println("--ConnectedLineNum: " + event.getConnectedLineNum());
            System.out.println("--ConnectedLineName: " + event.getConnectedLineName());
            System.out.println("--ChannelStateDesc: " + event.getChannelStateDesc());
            System.out.println("--Context: " + event.getContext());
            System.out.println("--ChannelState: " + event.getChannelState());

            AlertButton alertButton = alertService.findActiveButtonByPhone(phone);
            if (alertButton != null) { // Verificar si el teléfono existe en la base de datos
                logger.info("--addMessage " );
                alertMessage.addMessage(alertButton);
            }
        }
    }
}

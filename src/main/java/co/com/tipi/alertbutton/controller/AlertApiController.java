package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.Alert;
import co.com.tipi.alertbutton.domain.AlertButton;
import co.com.tipi.alertbutton.domain.ModelApiResponse;
import co.com.tipi.alertbutton.service.AlertService;
import co.com.tipi.alertbutton.service.ParameterService;
import co.com.tipi.alertbutton.sip.SipLiveEvents;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-21T20:53:56.302Z")

@CrossOrigin(origins = "*")
@Controller
public class AlertApiController implements AlertApi {

    private static final Logger logger = LoggerFactory.getLogger(AlertApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final AlertService alertService;


    @org.springframework.beans.factory.annotation.Autowired
    public AlertApiController(ObjectMapper objectMapper, HttpServletRequest request, AlertService alertService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.alertService = alertService;
    }

    @Override
    public ResponseEntity<ModelApiResponse> addAlert(@ApiParam(value = "Objecto botón que será agregado", required = true) @Valid @RequestBody Alert body) {
        logger.info("--addAlert ");
        String accept = request.getHeader("Accept");
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        alertService.addAlert(body);

        return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Alert>> listAlert(@ApiParam(value = "Teléfono") @Valid @RequestParam(value = "phone", required = false) String phone,@ApiParam(value = "Nombre") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "Teléfono") @Valid @RequestParam(value = "address", required = false) String address,@ApiParam(value = "Nombre de contacto") @Valid @RequestParam(value = "contactName", required = false) String contactName,@ApiParam(value = "Teléfono de contacto") @Valid @RequestParam(value = "contactPhone", required = false) String contactPhone,@ApiParam(value = "Estado") @Valid @RequestParam(value = "status", required = false) String status,@ApiParam(value = "Fecha de inicio") @Valid @RequestParam(value = "startDate", required = false) String startDate,@ApiParam(value = "Fecha fin") @Valid @RequestParam(value = "endDate", required = false) String endDate) {
        logger.info("--listAlert ");
        String accept = request.getHeader("Accept");
        List<Alert> list = new ArrayList<>();
        list = alertService.listAlert(phone, name, contactName, startDate, endDate);
        return new ResponseEntity<List<Alert>>(list, HttpStatus.OK);
    }


    /*
    public ResponseEntity<Void> addAlert(@ApiParam(value = "Objecto botón que será agregado" ,required=true )  @Valid @RequestBody Alert body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Alert>> listAlert(@ApiParam(value = "Nombre") @Valid @RequestParam(value = "name", required = false) String name, @ApiParam(value = "Estado") @Valid @RequestParam(value = "status", required = false) Integer status) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json; charset=utf-8")) {
            try {
                return new ResponseEntity<List<Alert>>(objectMapper.readValue("[ {  \"name\" : \"doggie\",  \"id\" : 0,  \"status\" : \"available\"}, {  \"name\" : \"doggie\",  \"id\" : 0,  \"status\" : \"available\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json; charset=utf-8", e);
                return new ResponseEntity<List<Alert>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/xml; charset=utf-8")) {
            try {
                return new ResponseEntity<List<Alert>>(objectMapper.readValue("<Alert>  <id>123456789</id>  <name>doggie</name>  <status>aeiou</status></Alert>", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml; charset=utf-8", e);
                return new ResponseEntity<List<Alert>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Alert>>(HttpStatus.NOT_IMPLEMENTED);
    }
    */
}

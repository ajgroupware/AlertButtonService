package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.AlertButton;
import co.com.tipi.alertbutton.domain.ModelApiResponse;
import co.com.tipi.alertbutton.service.ParameterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ParameterApiController implements ParameterApi {

    private static final Logger logger = LoggerFactory.getLogger(ParameterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final ParameterService parameterService;


    @org.springframework.beans.factory.annotation.Autowired
    public ParameterApiController(ObjectMapper objectMapper, HttpServletRequest request, ParameterService parameterService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.parameterService = parameterService;
    }

    @Override
    public ResponseEntity<ModelApiResponse> addButton(@ApiParam(value = "Objecto botón que será agregado" ,required=true )  @Valid @RequestBody AlertButton body) {
        logger.info("--addButton ");
        String accept = request.getHeader("Accept");
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        String response = parameterService.addButton(body);
        if ("OK".equals(response)) {
            return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.CREATED);
        } else {
            modelApiResponse.setMessage(response);
            return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<AlertButton> buttonByPhone(@ApiParam(value = "Teléfono",required=true) @PathVariable("phone") String phone) {
        String accept = request.getHeader("Accept");
        AlertButton alertButton = parameterService.getButtonByPhone(phone);
        return new ResponseEntity<AlertButton>(alertButton, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AlertButton>> listButton(@ApiParam(value = "Teléfono") @Valid @RequestParam(value = "phone", required = false) String phone,@ApiParam(value = "Nombre") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "Teléfono") @Valid @RequestParam(value = "address", required = false) String address,@ApiParam(value = "Nombre de contacto") @Valid @RequestParam(value = "contactName", required = false) String contactName,@ApiParam(value = "Teléfono de contacto") @Valid @RequestParam(value = "contactPhone", required = false) String contactPhone,@ApiParam(value = "Estado") @Valid @RequestParam(value = "status", required = false) String status) {
        logger.info("--listButton ");
        String accept = request.getHeader("Accept");
        List<AlertButton> list = new ArrayList<>();
        list = parameterService.listButton(phone, name, contactName);
        return new ResponseEntity<List<AlertButton>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ModelApiResponse> updateButton(@ApiParam(value = "Objecto botón que será actualizado" ,required=true )  @Valid @RequestBody AlertButton body) {
        logger.info("--updateButton ");
        String accept = request.getHeader("Accept");
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        String response = parameterService.updateButton(body);
        if ("OK".equals(response)) {
            return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.CREATED);
        } else {
            modelApiResponse.setMessage(response);
            return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
    @Override
    public ResponseEntity<ModelApiResponse> addButton(@ApiParam(value = "Objecto botón que será agregado" ,required=true )  @Valid @RequestBody AlertButton body) {
        String accept = request.getHeader("Accept");
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        parameterService.addButton(body);


        return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.CREATED);
    }


    public ResponseEntity<List<AlertButton>> listButton(@ApiParam(value = "Nombre") @Valid @RequestParam(value = "name", required = false) String name, @ApiParam(value = "Estado") @Valid @RequestParam(value = "status", required = false) Integer status) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json; charset=utf-8")) {
            try {
                return new ResponseEntity<List<AlertButton>>(objectMapper.readValue("[ {  \"name\" : \"doggie\",  \"id\" : 0,  \"status\" : \"available\"}, {  \"name\" : \"doggie\",  \"id\" : 0,  \"status\" : \"available\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json; charset=utf-8", e);
                return new ResponseEntity<List<AlertButton>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/xml; charset=utf-8")) {
            try {
                return new ResponseEntity<List<AlertButton>>(objectMapper.readValue("<AlertButton>  <id>123456789</id>  <name>doggie</name>  <status>aeiou</status></AlertButton>", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml; charset=utf-8", e);
                return new ResponseEntity<List<AlertButton>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<AlertButton>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModelApiResponse> updateButton(@ApiParam(value = "Objecto botón que será actualizado" ,required=true )  @Valid @RequestBody AlertButton body) {
        String accept = request.getHeader("Accept");
        ModelApiResponse modelApiResponse = new ModelApiResponse();


        return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.CREATED);
    }
    */
}

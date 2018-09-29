package co.com.tipi.alertbutton.controller;

import co.com.tipi.alertbutton.domain.AlertButton;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;

public interface TestControllerApi {
    @RequestMapping(value = "/test",
            produces = { "application/xml; charset=utf-8", "application/json; charset=utf-8" },
            consumes = { "application/json; charset=utf-8", "application/xml; charset=utf-8" },
            method = RequestMethod.POST)
    ResponseEntity<Void> addMessage(@Valid @RequestBody AlertButton body);

}

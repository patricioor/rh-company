package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.service.RemuneracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/remuneracao")
public class RemuneracaoController {

    private final RemuneracaoService service;

    @Autowired
    public RemuneracaoController(RemuneracaoService service) {
        this.service = service;
    }

}

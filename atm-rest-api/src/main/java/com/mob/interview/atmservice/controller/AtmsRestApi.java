package com.mob.interview.atmservice.controller;

import com.mob.interview.atmservice.model.Atm;
import com.mob.interview.atmservice.service.AtmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/atm")
@RequiredArgsConstructor
public class AtmsRestApi {

    private final AtmService atmService;

    @RequestMapping(value = "/getAllAtms", method = RequestMethod.GET)
    public List<Atm> getAll() throws Exception {
        return atmService.getAllAtms(null);
    }

    @RequestMapping(value = "/{city}/getAllAtmsByCityName", method = RequestMethod.GET)
    public List<Atm> getAllAtmsByCityName(@PathVariable String city) throws Exception {
        return atmService.getAllAtms(city);
    }
}

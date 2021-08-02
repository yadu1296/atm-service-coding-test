package com.mob.interview.atmservice.service;

import com.mob.interview.atmservice.model.Atm;

import java.util.List;

public interface AtmService {

    List<Atm> getAllAtms(String filter) throws Exception;
}

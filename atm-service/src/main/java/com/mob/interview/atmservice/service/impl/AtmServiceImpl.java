package com.mob.interview.atmservice.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mob.interview.atmservice.model.Atm;
import com.mob.interview.atmservice.service.AtmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AtmServiceImpl implements AtmService {

    private final RestTemplate restTemplate;


    @Override
    public List<Atm> getAllAtms(String filter) throws Exception {
        ArrayList<Atm> atmsList = new ArrayList<>();
        try {
            log.info("Calling third party to get the list of atms.");
            ResponseEntity<String> response = restTemplate.exchange("https://www.ing.nl/api/locator/atms/", HttpMethod.GET, new HttpEntity<>("", buildHeaders()), String.class);

            boolean isJsonString = isJSONValid(response.getBody());
            String res = response.getBody();
            if (isJsonString) {
                atmsList = new Gson().fromJson(res, new TypeToken<ArrayList<Atm>>() {
                }.getType());
            } else {
                if (!StringUtils.isEmpty(res)) {
                    res = res.substring(6, res.length());
                    JsonReader reader = new JsonReader(new StringReader(res));
                    reader.setLenient(true);
                    atmsList = new Gson().fromJson(reader, new TypeToken<ArrayList<Atm>>() {
                    }.getType());
                }
            }

        } catch (Exception e) {
            log.info("Exception e:{}", e.getMessage());
            throw new Exception("Unable to fetch the data");
        }
        if (!StringUtils.isEmpty(filter)) {
           List<Atm> filterList = atmsList.stream()
                   .filter(atm -> Objects.nonNull(atm.getAddress()))
                   .filter(atm -> Objects.nonNull(atm.getAddress().getCity()))
                   .filter(atm -> atm.getAddress().getCity().equals(filter))
                   .collect(Collectors.toList());
           return filterList;
        }
        return atmsList;
    }
    public static boolean isJSONValid(String string) {
        try {
           new JSONObject(string);
        } catch (JSONException ex) {
            try {
                new JSONArray(string);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    private HttpHeaders buildHeaders() {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setConnection("keep-alive");
            return headers;

    }

}

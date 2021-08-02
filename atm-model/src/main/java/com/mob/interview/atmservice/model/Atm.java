package com.mob.interview.atmservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mob.interview.atmservice.model.enums.AtmType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Atm {

    private Address address;
    private List<OpeningHours> openingHours;
    private Long distance;
    private String functionality;
    private AtmType atmType;
}

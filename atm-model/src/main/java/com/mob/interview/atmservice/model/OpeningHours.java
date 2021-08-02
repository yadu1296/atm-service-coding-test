package com.mob.interview.atmservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpeningHours {
    private Integer dayOfWeek;
    private List<Hours> hours;
}

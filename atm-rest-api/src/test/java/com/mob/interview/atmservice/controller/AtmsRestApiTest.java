package com.mob.interview.atmservice.controller;

import com.mob.interview.atmservice.service.AtmService;
import com.mob.interview.atmservice.service.impl.AtmServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AtmsRestApiTest {

    private MockMvc mockMvc;

    AtmService atmservice = mock(AtmServiceImpl.class);

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AtmsRestApi(atmservice)).build();
    }


    @Test
    public void testGetAllAtms() throws Exception {

        mockMvc.perform(get("/api/atm/getAllAtms")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetAllAtmsByCityName() throws Exception {

        mockMvc.perform(get("/api/atm/Mijdrecht/getAllAtmsByCityName")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}

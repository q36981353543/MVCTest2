package com.springapp.mvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class MainControllerTest {
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPrintCommand() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/commands/test/1"))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value("OK"));
    }

    @Test
    public void testPrintCommandErrorSituation() throws Exception {
        this.mockMvc.perform(get("/commands/test/t"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value("ERROR"));
    }

    @Test
    public void testDoPost() throws Exception {
        this.mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceId\":\"test\",\"command\":\"test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value("OK"));
    }
}
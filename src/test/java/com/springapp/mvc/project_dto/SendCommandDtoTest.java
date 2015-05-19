package com.springapp.mvc.project_dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class SendCommandDtoTest {

    @Test
    public void testSetAndGetDeviceId() throws Exception {
        SendCommandDto sendCommandDto = new SendCommandDto();
        sendCommandDto.setDeviceId("test");
        Assert.assertEquals("test", sendCommandDto.getDeviceId());
    }

    @Test
    public void testSetAndGetCommand() throws Exception {
        SendCommandDto sendCommandDto = new SendCommandDto();
        sendCommandDto.setCommand("test");
        Assert.assertEquals("test", sendCommandDto.getCommand());
    }
}
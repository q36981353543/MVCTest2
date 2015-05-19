package com.springapp.mvc.project_dto;

import com.springapp.mvc.project_data.Command;
import com.springapp.mvc.project_data.ResultCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class GetCommandResponseDtoTest {

    @Test
    public void testSetAndGetCommandId() throws Exception {
        GetCommandResponseDto getCommandResponseDto = new GetCommandResponseDto(new Command(new SendCommandDto()));
        getCommandResponseDto.setCommandId(1);
        Assert.assertEquals(1, getCommandResponseDto.getCommandId());
    }

    @Test
    public void testSetAndGetCommand() throws Exception {
        GetCommandResponseDto getCommandResponseDto = new GetCommandResponseDto(new Command(new SendCommandDto()));
        getCommandResponseDto.setCommand("test");
        Assert.assertEquals("test", getCommandResponseDto.getCommand());
    }

    @Test
    public void testGetResultCode() throws Exception {
        GetCommandResponseDto getCommandResponseDto = new GetCommandResponseDto(new Command(new SendCommandDto()));
        getCommandResponseDto.setResultCode(ResultCode.OK);
        Assert.assertEquals(ResultCode.OK, getCommandResponseDto.getResultCode());
    }
}
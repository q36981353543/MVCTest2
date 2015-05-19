package com.springapp.mvc.project_data;

import com.springapp.mvc.project_dto.SendCommandDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class CommandTest {

    @Test
    public void testGetCommandId() throws Exception {
        Command c0 = new Command(new SendCommandDto("d1","test"));
        Command c1 = new Command(new SendCommandDto("d1","test"));
        Command c2 = new Command(new SendCommandDto("d1","test"));
        Assert.assertEquals(2,c2.getCommandId());
    }

    @Test
    public void testSetAndGetDeviceId() throws Exception {
        Command command = new Command(new SendCommandDto("d1","test"));
        command.setDeviceId("d2");
        Assert.assertEquals("d2",command.getDeviceId());
    }

    @Test
    public void testSetAndGetCommand() throws Exception {
        Command command = new Command(new SendCommandDto("test","c1"));
        command.setCommand("c2");
        Assert.assertEquals("c2", command.getCommand());
    }
}
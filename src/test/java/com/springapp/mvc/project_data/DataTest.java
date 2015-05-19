package com.springapp.mvc.project_data;

import com.springapp.mvc.project_dto.SendCommandDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DataTest {
    @Autowired
    Data data;

    @Test
    public void testDataAddAndRemove() throws Exception {
        data.add(new Command(new SendCommandDto("test","test")));
        Assert.assertEquals("test", data.remove("test").getDeviceId());
    }

    @Test
    public void testCorrectCommandId() throws Exception {
        data.add(new Command(new SendCommandDto("d1","test")));
        data.add(new Command(new SendCommandDto("d1","test")));
        data.add(new Command(new SendCommandDto("d1","test")));
        data.add(new Command(new SendCommandDto("d2","test")));
        data.remove("d1").getCommandId();
        data.remove("d2").getCommandId();
        data.remove("d1").getCommandId();
        Assert.assertEquals(2, data.remove("d1").getCommandId());
    }
}
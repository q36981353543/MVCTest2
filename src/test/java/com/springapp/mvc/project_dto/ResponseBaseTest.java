package com.springapp.mvc.project_dto;

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
public class ResponseBaseTest {

    @Test
    public void testSetAndGetResultCode() throws Exception {
        ResponseBase responseBase = new ResponseBase(ResultCode.ERROR);
        responseBase.setResultCode(ResultCode.OK);
        Assert.assertEquals(ResultCode.OK, responseBase.getResultCode());
    }
}
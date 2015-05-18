package com.springapp.mvc.services;

import com.springapp.mvc.project_data.ResultCode;
import com.springapp.mvc.project_dto.ResponseBase;
import com.springapp.mvc.project_dto.SendCommandDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.async.DeferredResult;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class DeferredResultServiceTest {
    @Autowired
    DeferredResultService deferredResultService;

    @Test
    public void testPrintCommand() throws Exception {
        DeferredResult<ResponseBase> responseBaseDeferredResult = deferredResultService.printCommand("test", 1);
        ResponseBase responseBase = (ResponseBase) responseBaseDeferredResult.getResult();
        Assert.assertEquals(responseBase, ResultCode.OK);   //failed
    }

    @Test
    public void testAddCommand() throws Exception {
        Assert.assertEquals(deferredResultService.addCommand(new SendCommandDto("test","test")).getResultCode(), ResultCode.OK);
    }
}
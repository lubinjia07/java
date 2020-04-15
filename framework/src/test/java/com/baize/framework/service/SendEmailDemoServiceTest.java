package com.baize.framework.service; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import org.springframework.beans.factory.annotation.Autowired;

/** 
* SendEmailDemoService Tester. 
* 
* @author <Authors name> 
* @since <pre>04/16/2020</pre> 
* @version 1.0 
*/ 
public class SendEmailDemoServiceTest extends BaseTest{ 
    @Autowired
    private SendEmailDemoService sendEmailDemoService;
    
    @Before
    public void before() throws Exception {
        
    }

    @After
    public void after() throws Exception {
        
    }
    
        
    @Test
    public void testSendTxtMail() throws Exception {
        
    }
        
    @Test
    public void testSendHtmlMail() throws Exception {
        
    }
        
    @Test
    public void testSendAttachedImageMail() throws Exception {
        
    }
        
    @Test
    public void testSendAttendedFileMail() throws Exception {
        
    }
        
    @Test
    public void testSendTemplateMail() throws Exception {
        sendEmailDemoService.sendTemplateMail();
    }

}

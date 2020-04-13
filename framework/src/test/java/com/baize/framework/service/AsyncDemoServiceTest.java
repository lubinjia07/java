package com.baize.framework.service; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import org.springframework.beans.factory.annotation.Autowired;

/** 
* AsyncDemoService Tester. 
* 
* @author <Authors name> 
* @since <pre>04/11/2020</pre> 
* @version 1.0 
*/ 
public class AsyncDemoServiceTest extends BaseTest{ 
    @Autowired
    private AsyncDemoService AsyncDemoService;
    
    @Before
    public void before() throws Exception {
        
    }

    @After
    public void after() throws Exception {
        
    }
    

    @Test
    public void testMethod1() throws Exception {
        AsyncDemoService.method1();
    }

    @Test
    public void testMethod2() throws Exception {
        
    }

}

package com.baize.framework.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FreeMarkerDemoService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>04/13/2020</pre>
 */
public class FreeMarkerDemoServiceTest extends BaseTest {
    @Autowired
    private FreeMarkerDemoService freeMarkerDemoService;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    @Test
    public void testGetContext() throws Exception {
        String content = freeMarkerDemoService.getContext();
        System.out.println(content);
    }

}

package com.baize.framework.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DynamicProxyUtil {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public void trans(Runnable runnable){
        runnable.run();
    }

    @Async
    public void asyncs(Runnable runnable){
        runnable.run();
    }

}

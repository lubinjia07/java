package com.baize.framework.util;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

/**
 * 事务代理类
 * 同一个类中调用异步@Transactional，使用此代理类处理
 * @author lubinjia
 * @create 2020/4/10 23:52
 */
@Component
public class TransProxy<T> {
    /**
     * 执行事务，无返回参数
     * @param runnable
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public void trans(Runnable runnable) {
        runnable.run();
    }

    /**
     * 执行事务，带返回参数
     * T 可以传入ResultResponse，用来接收事务执行的结果
     * @param runnable
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public T trans(Supplier<T> runnable) {
        return runnable.get();
    }

}

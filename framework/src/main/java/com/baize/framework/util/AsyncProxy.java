package com.baize.framework.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 异步代理类
 * 同一个类中调用异步@Async，使用此代理类处理
 * @author lubinjia
 * @create 2020/4/10 23:52
 */
@Component
public class AsyncProxy<T> {
    /**
     * 执行异步，无返回参数
     * @param runnable
     */
    @Async
    public void async(Runnable runnable){
        runnable.run();
    }

    /**
     * 执行异步，带返回参数
     * T 可以传入ResultResponse，用来接收事务执行的结果
     * @param runnable
     * @return
     */
    @Async
    public T async(Supplier<T> runnable){
        return runnable.get();
    }

}

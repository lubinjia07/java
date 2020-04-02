package com.baize.framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 线程池工具类
 * @Author: lbj
 * @Description： 包装成工具类，直接静态调用
 * @Date： create in 2020/4/2 23:20
 */
@Component
public class ThreadPoolUtil {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static ThreadPoolUtil threadPoolUtil;

    @PostConstruct
    public void init(){
        threadPoolUtil = this;
    }

    /**
     * execute
     * @param task
     */
    public static void execute(Runnable task){
        threadPoolUtil.threadPoolTaskExecutor.execute(task);
    }

    /**
     * submit
     * @param task
     * @return
     */
    public static Future<?> submit(Runnable task){
        return threadPoolUtil.threadPoolTaskExecutor.submit(task);
    }

    /**
     * submit
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> submit(Callable<T> callable){
        return threadPoolUtil.threadPoolTaskExecutor.submit(callable);
    }

    /**
     * submitListenable
     * @param task
     * @return
     */
    public static ListenableFuture<?> submitListenable(Runnable task){
        return threadPoolUtil.threadPoolTaskExecutor.submitListenable(task);
    }

    /**
     * submitListenable
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> submitListenable(Callable<T> callable){
        return threadPoolUtil.threadPoolTaskExecutor.submitListenable(callable);
    }

}

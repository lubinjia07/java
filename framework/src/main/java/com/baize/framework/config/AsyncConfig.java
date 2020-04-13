package com.baize.framework.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 异步配置类
 * @Author: lbj
 * @Description:
 * @Date: create in 2020/4/2 23:58
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Value("${thread.pool.core.pool.size}")
    private int threadPoolCorePoolSize;
    @Value("${thread.pool.max.pool.size}")
    private int threadPoolMaxPoolSize;
    @Value("${thread.pool.queue.capacity}")
    private int threadPoolQueueCapacity;
    @Value("${thread.pool.keep.alive.seconds}")
    private int threadPoolKeepAliveSeconds;
    @Value("${async.name.prefix}")
    private String threadPoolNamePrefix;

    /**
     * 初始化
     * 参数借用线程池参数
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(threadPoolCorePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolMaxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolNamePrefix);
        return threadPoolTaskExecutor;
    }

    /**
     * 异常处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return new AsyncUncaughtExceptionHandler() {
//            @Override
//            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
////                logger.error("线程池执行任务发生未知异常.", ex);
//            }
//        };
        return (ex, method, params) -> {
//                logger.error("线程池执行任务发生未知异常.", ex);
        };
    }

}

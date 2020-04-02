package com.baize.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池工具类
 * @Author: lbj
 * @Description:
 * @Date: create in 2020/4/2 23:20
 */
@Configuration
public class ThreadPoolConfig {

    @Value("thread.pool.core.pool.size")
    private int threadPoolCorePoolSize;
    @Value("thread.pool.max.pool.size")
    private int threadPoolMaxPoolSize;
    @Value("thread.pool.queue.capacity")
    private int threadPoolQueueCapacity;
    @Value("thread.pool.keep.alive.seconds")
    private int threadPoolKeepAliveSeconds;
    @Value("thread.pool.name.prefix")
    private String threadPoolNamePrefix;

//    @Primary
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /** 核心线程数 **/
        threadPoolTaskExecutor.setCorePoolSize(threadPoolCorePoolSize);
        /** 最大线程数 **/
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolMaxPoolSize);
        /** 队列最大长度 **/
        threadPoolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
        /** 线程池维护线程所允许的空闲时间，默认为60s **/
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);
        /* 线程池中线程的名称前缀 */
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolNamePrefix);
        /**
         * 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
         *
         * AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常
         * CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度
         * DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行
         * DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行
         */
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}

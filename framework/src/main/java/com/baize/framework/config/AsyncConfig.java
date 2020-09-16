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
 * Spring异步配置类（@Async的配置，配置可以和ThreadPoolConfig相同）
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
        //核心线程数，根据服务器CPU核心数确定
        threadPoolTaskExecutor.setCorePoolSize(threadPoolCorePoolSize);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolMaxPoolSize);
        //缓存队列最大长度
        threadPoolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
        //线程池维护线程所允许的空闲时间，默认为60s
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);
        //线程池中线程的名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolNamePrefix);
        return threadPoolTaskExecutor;
    }

//    /**
//     * 异常处理
//     * @return
//     */
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
////        return new AsyncUncaughtExceptionHandler() {
////            @Override
////            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
//////                logger.error("线程池执行任务发生未知异常.", ex);
////            }
////        };
//        return (ex, method, params) -> {
////                logger.error("线程池执行任务发生未知异常.", ex);
//        };
//    }

    /**
     * 异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    private static class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            //to do 记录日志
//            log.info ...
            System.out.println("异步异常：" + throwable.getMessage());
            if(throwable instanceof AsyncException){
                AsyncException asyncException = (AsyncException) throwable;
//                asyncException.getErrorMessage();
            }
            throwable.printStackTrace();
        }
    }

    public static class AsyncException extends Exception{
        private int code;
        private String errorMessage;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

}

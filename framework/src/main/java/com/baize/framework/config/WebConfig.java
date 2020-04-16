//package com.baize.framework.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.BeanNameViewResolver;
//import org.springframework.web.servlet.view.InternalResourceView;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import java.io.File;
//import java.util.Locale;
//
///**
// * @author lubinjia
// * @create 2020/4/16 23:39
// */
//@Configuration
//public class WebConfig extends WebMvcConfigurationSupport {
//    //配置JSP视图解析器
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/view/");
//        resolver.setSuffix(".html");
//        resolver.setExposeContextBeansAsAttributes(true);
//        return resolver;
//    }
//
//   /* @Bean
//    public InternalResourceViewResolver htmlViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/");
//        viewResolver.setViewClass(HandleResourceViewExists.class); //设置检查器
//        viewResolver.setSuffix(".html");
//        viewResolver.setOrder(0);
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        return viewResolver;
//    }
//
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(HandleResourceViewExists.class); //设置检查器
//        viewResolver.setPrefix("/WEB-INF/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setOrder(0);
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        return viewResolver;
//    }*/
//
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/yuan").setViewName("success");
////    }
//
////    @Bean
////    public BeanNameViewResolver beanNameViewResolver(){
////        BeanNameViewResolver resolver = new BeanNameViewResolver();
////        return resolver;
////    }
//
////    //ViewResolver 实现了试图解析器的接口的类，我们就可以把它看作一个视图解析器
////    @Bean
////    public ViewResolver myViewResolver(){
////        return new MyViewResolver();
////    }
////
////    public static class MyViewResolver implements ViewResolver{
////
////        @Override
////        public View resolveViewName(String s, Locale locale) throws Exception {
////            return null;
////        }
////    }
//
//}

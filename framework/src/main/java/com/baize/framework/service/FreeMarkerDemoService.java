package com.baize.framework.service;

import com.baize.framework.util.FreeMarkerUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lubinjia
 * @create 2020/4/13 23:10
 */
@Service
public class FreeMarkerDemoService {
    @Autowired
    private FreeMarkerUtil freeMarkerUtil;
    @Autowired
    private FreeMarkerConfigurer freemarker;

    public void getContext() {
        // 获取模版对象
        try {
            // 获取模版对象
            Template template = freeMarkerUtil.getTemplate("user.ftl");
            // 准备的 model data
            Map<String, User> map = new HashMap<String, User>();
            map.put("user", new User("Hellen", 20));
//                freeMarkerUtil.getTemplateContent("user.ftl", map);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static class User {
        private String userName;

        private Integer age;

        public User(String userName, Integer age) {
            this.userName = userName;
            this.age = age;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

}

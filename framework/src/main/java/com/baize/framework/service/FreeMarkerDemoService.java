package com.baize.framework.service;

import com.baize.framework.domain.User;
import com.baize.framework.util.FreeMarkerUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String getContext() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("user", new User(1, "Helen"));
            return freeMarkerUtil.getTemplateContent("user.ftl", map);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

}

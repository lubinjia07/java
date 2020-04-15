package com.baize.framework.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Map;

/**
 * @author lubinjia
 * @create 2020/4/13 23:03
 */
@Component
public class FreeMarkerUtil {
    private Configuration cfg;

    @Resource
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        //指定模板路径
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
    }

    /**
     * 在模板文件目录中找到名称为name的文件
     * @param name
     * @return
     * @throws IOException
     */
    public Template getTemplate(String name) throws IOException {
        return cfg.getTemplate(name);
    }

    /**
     * 传入参数解析出模板内容
     * @param name
     * @param params
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String getTemplateContent(String name, Map<String, Object> params) throws IOException, TemplateException {
        Template template = cfg.getTemplate(name);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
    }

}

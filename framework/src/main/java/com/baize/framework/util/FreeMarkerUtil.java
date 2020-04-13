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
    private Configuration cfg = null;

//	@Autowired
//	private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        //通过freemarkerd Configuration读取相应的ftl
        cfg = new Configuration();
//        cfg = new Configuration(Configuration.VERSION_2_3_23);
        //设定去哪里读取相应的ftl模板文件，指定模板路径
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
    }

    public Template getTemplate(String name){
        try {
            //在模板文件目录中找到名称为name的文件
            Template template =  cfg.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTemplateContent(String name, Map<String, Object> params){
        try {
            //在模板文件目录中找到名称为name的文件
            Template template =  cfg.getTemplate(name);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
            return content;
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public String getHtmlTemplate(String ftlFile, Object dataModel) {
//        String htmlTemplate = "";
//        try {
//            Template template = cfg.getTemplate(ftlFile, "UTF-8");
//            Writer out = new StringWriter();
//            template.process(dataModel, out);
//            htmlTemplate = out.toString();
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
//        return htmlTemplate;
//    }

}

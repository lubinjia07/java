package com.baize.framework.dto.email;

import lombok.Data;
import lombok.Delegate;
import lombok.ToString;

import java.util.Map;

/**
 * @author lubinjia
 * @create 2020/4/16 1:02
 */
@Data
public class TemplateEmailDto extends MailDto{
    String templateName;
    Map<String, Object> model;
}

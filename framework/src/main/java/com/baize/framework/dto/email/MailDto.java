package com.baize.framework.dto.email;

import lombok.Data;

/**
 * @author lubinjia
 * @create 2020/4/16 0:38
 */
@Data
public class MailDto {
    //主题
    String subject;
    //内容
    String content;
    //发件人
    String sender;
    //收件人
    String receiver;
}

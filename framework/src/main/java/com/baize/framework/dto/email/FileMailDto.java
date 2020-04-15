package com.baize.framework.dto.email;

import lombok.Data;

/**
 * @author lubinjia
 * @create 2020/4/16 0:57
 */
@Data
public class FileMailDto extends MailDto {
    String filePath;
    String fileName;
}

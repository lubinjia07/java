package com.baize.framework.dto.email;

import lombok.Data;

/**
 * @author lubinjia
 * @create 2020/4/16 0:53
 */
@Data
public class ImageEmailDto extends MailDto{
    String imagePath;
    String imageId;
}

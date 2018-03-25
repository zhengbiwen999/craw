package com.craw.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by zbww on 2018/3/22.
 */
@Data
public class BaseEntity {

    private int id;

    private String imgUrl;

    private String pageUrl;

    private Date createTime;

    private String keyWords;

    private String downLoadUrl;

    private String vertifyCode;

}

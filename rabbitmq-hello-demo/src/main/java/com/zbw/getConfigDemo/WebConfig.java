package com.zbw.getConfigDemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zbww on 2018/3/7.
 */
@Data
@Component
@ConfigurationProperties(prefix = "home")
public class WebConfig {

    private String province;

    private String city;

    private String desc;

}

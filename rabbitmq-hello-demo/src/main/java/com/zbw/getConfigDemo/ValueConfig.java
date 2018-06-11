package com.zbw.getConfigDemo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "zuul.app")
@Data
@EqualsAndHashCode(callSuper = false)
public class ValueConfig {

    private String env;
}

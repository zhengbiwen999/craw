package com.zbw;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "test.value")
@Data
@EqualsAndHashCode(callSuper = false)
public class ValueConfig {

    private int num1;

    private int num2;
}

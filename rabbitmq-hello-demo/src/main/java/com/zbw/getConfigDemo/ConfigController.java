package com.zbw.getConfigDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Autowired
    private ValueConfig valueConfig;


    @Autowired
    private WebConfig webConfig;

    /**
     * http://localhost:8083/bus/refresh
     * 更改配置后，post上述接口即可，即可更新为最新的配置信息
     */
    @RequestMapping("/getRemoteConfig")
    public String getRemoteConfig(){
        return "读取config配置值 ："+valueConfig.getEnv();
    }

    /**
     * 根据dev 、test 环境，在 application-{profile}.yml 中获取配置信息
     */
    @RequestMapping("/getConfig")
    String getConfig(){
        String value = webConfig.getProvince()+": "+webConfig.getCity()+": "+webConfig.getDesc();
        return value;
    }
}

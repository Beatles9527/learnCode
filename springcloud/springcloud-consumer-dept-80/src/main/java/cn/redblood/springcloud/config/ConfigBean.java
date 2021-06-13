package cn.redblood.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wantao
 * @Configuration => spring 里的 applicationContext.xml
 */
@Configuration
public class ConfigBean {

    // 配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced // Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}

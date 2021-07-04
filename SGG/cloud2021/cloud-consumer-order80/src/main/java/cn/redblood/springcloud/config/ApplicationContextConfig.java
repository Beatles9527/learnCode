package cn.redblood.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author charlie
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
     *
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}

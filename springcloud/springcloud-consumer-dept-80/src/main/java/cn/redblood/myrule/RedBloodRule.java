package cn.redblood.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

public class RedBloodRule {

    @Bean
    public IRule myRule(){
        return new MyRandomRule();
    }
}

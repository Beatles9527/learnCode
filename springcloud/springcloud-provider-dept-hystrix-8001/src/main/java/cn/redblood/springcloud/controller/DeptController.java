package cn.redblood.springcloud.controller;

import cn.redblood.springcloud.pojo.Dept;
import cn.redblood.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wantao
 */
@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixById")
    public Dept getById(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("deptService.queryById(id)存在错误！==>" + id);
        }
        return dept;
    }

    public Dept hystrixById(@PathVariable("id") Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id=>" + "没有对应的信息，null--@Hystrix")
                .setDb_source("no this database in Mysql");
    }
}

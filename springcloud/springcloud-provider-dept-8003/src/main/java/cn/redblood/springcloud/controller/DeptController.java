package cn.redblood.springcloud.controller;

import cn.redblood.springcloud.pojo.Dept;
import cn.redblood.springcloud.service.DeptService;
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

    @Resource
    private DiscoveryClient client;

    @PostMapping("add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.queryById(id);
    }

    @GetMapping("list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }

    @GetMapping("discovery")
    public Object discovery() {
        List<String> services = client.getServices();
        log.warn("微服务列表清单: {}", services);

        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        int i = 0;
        for (ServiceInstance instance : instances) {
            i++;
            System.out.println("第" + i + "个实例的Host: {}" + instance.getHost());
            System.out.println("第" + i + "个实例的Post: {}" + instance.getPort());
            System.out.println("第" + i + "个实例的Uri: {}" + instance.getUri());
            System.out.println("第" + i + "个实例的ServiceId: {}" + instance.getServiceId());
        }
        return this.client;
    }
}

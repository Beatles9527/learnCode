package cn.redblood.springcloud.controller;

import cn.redblood.springcloud.pojo.Dept;
import cn.redblood.springcloud.service.DeptClientService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wantao
 */
@RestController
public class DeptConsumerController {


    @Resource
    private DeptClientService service;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return service.queryById(id);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return service.addDept(dept);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> getList() {
        return service.queryAll();
    }

}

package cn.redblood.springcloud.controller;

import cn.redblood.springcloud.pojo.Dept;
import cn.redblood.springcloud.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wantao
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Resource
    private DeptService deptService;

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
}

package cn.redblood.springcloud.service;

import cn.redblood.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author charlie
 */
@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @GetMapping("dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("dept/list")
    List<Dept> queryAll();

    @PostMapping("dept/add")
    boolean addDept(Dept dept);
}

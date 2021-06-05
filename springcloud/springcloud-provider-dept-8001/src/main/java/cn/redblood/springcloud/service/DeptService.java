package cn.redblood.springcloud.service;

import cn.redblood.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();
}

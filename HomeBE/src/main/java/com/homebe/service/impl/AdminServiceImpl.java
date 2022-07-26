package com.homebe.service.impl;

import com.homebe.dao.AdminDao;
import com.homebe.entity.Admin;
import com.homebe.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-02-24 12:44:46
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param adminName 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(String adminName) {
        return this.adminDao.queryById(adminName);
    }

    //查询所有
    public List<Admin> queryAll() {
        return this.adminDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param admin       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Admin> queryByPage(Admin admin, PageRequest pageRequest) {
        long total = this.adminDao.count(admin);
        return new PageImpl<>(this.adminDao.queryAllByLimit(admin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getAdminName());
    }

    /**
     * 通过主键删除数据
     *
     * @param adminName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String adminName) {
        return this.adminDao.deleteById(adminName) > 0;
    }
}

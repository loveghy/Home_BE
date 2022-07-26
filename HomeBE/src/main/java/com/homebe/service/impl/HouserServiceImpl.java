package com.homebe.service.impl;

import com.homebe.dao.HouserDao;
import com.homebe.entity.Houser;
import com.homebe.service.HouserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Houser)表服务实现类
 *
 * @author makejava
 * @since 2022-02-26 21:11:59
 */
@Service("houserService")
public class HouserServiceImpl implements HouserService {
    @Resource
    private HouserDao houserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param houserId 主键
     * @return 实例对象
     */
    @Override
    public Houser queryById(String houserId) {
        return this.houserDao.queryById(houserId);
    }

    //查询所有
    public List<Houser> queryAll() {
        return this.houserDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param houser      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Houser> queryByPage(Houser houser, PageRequest pageRequest) {
        long total = this.houserDao.count(houser);
        return new PageImpl<>(this.houserDao.queryAllByLimit(houser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param houser 实例对象
     * @return 实例对象
     */
    @Override
    public Houser insert(Houser houser) {
        this.houserDao.insert(houser);
        return houser;
    }

    /**
     * 修改数据
     *
     * @param houser 实例对象
     * @return 实例对象
     */
    @Override
    public Houser update(Houser houser) {
        this.houserDao.update(houser);
        return this.queryById(houser.getHouserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param houserId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String houserId) {
        return this.houserDao.deleteById(houserId) > 0;
    }
}

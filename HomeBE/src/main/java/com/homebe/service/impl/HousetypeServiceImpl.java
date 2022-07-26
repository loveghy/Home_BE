package com.homebe.service.impl;

import com.homebe.dao.HousetypeDao;
import com.homebe.entity.Housetype;
import com.homebe.service.HousetypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Housetype)表服务实现类
 *
 * @author makejava
 * @since 2022-03-14 22:18:56
 */
@Service("housetypeService")
public class HousetypeServiceImpl implements HousetypeService {
    @Resource
    private HousetypeDao housetypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Housetype queryById(Integer id) {
        return this.housetypeDao.queryById(id);
    }

    //查询所有
    public List<Housetype> queryAll() {
        return this.housetypeDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param housetype   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Housetype> queryByPage(Housetype housetype, PageRequest pageRequest) {
        long total = this.housetypeDao.count(housetype);
        return new PageImpl<>(this.housetypeDao.queryAllByLimit(housetype, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     * @param housetype 实例对象
     * @return 实例对象
     */
    @Override
    public Housetype insert(Housetype housetype) {
        this.housetypeDao.insert(housetype);
        return housetype;
    }

    /**
     * 修改数据
     *
     * @param housetype 实例对象
     * @return 实例对象
     */
    @Override
    public Housetype update(Housetype housetype) {
        this.housetypeDao.update(housetype);
        return this.queryById(housetype.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.housetypeDao.deleteById(id) > 0;
    }
}

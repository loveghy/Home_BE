package com.homebe.service.impl;

import com.homebe.dao.CustomDao;
import com.homebe.entity.Custom;
import com.homebe.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Custom)表服务实现类
 *
 * @author makejava
 * @since 2022-02-26 21:12:25
 */
@Service("customService")
public class CustomServiceImpl implements CustomService {
    @Resource
    private CustomDao customDao;

    /**
     * 通过ID查询单条数据
     *
     * @param customId 主键
     * @return 实例对象
     */
    @Override
    public Custom queryById(String customId) {
        return this.customDao.queryById(customId);
    }

    //查询所有
    public List<Custom> queryAll() {
        return this.customDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param custom      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Custom> queryByPage(Custom custom, PageRequest pageRequest) {
        long total = this.customDao.count(custom);
        return new PageImpl<>(this.customDao.queryAllByLimit(custom, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param custom 实例对象
     * @return 实例对象
     */
    @Override
    public Custom insert(Custom custom) {
        this.customDao.insert(custom);
        return custom;
    }

    /**
     * 修改数据
     *
     * @param custom 实例对象
     * @return 实例对象
     */
    @Override
    public Custom update(Custom custom) {
        this.customDao.update(custom);
        return this.queryById(custom.getCustomId());
    }

    /**
     * 通过主键删除数据
     *
     * @param customId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String customId) {
        return this.customDao.deleteById(customId) > 0;
    }
}

package com.homebe.service;

import com.homebe.entity.Custom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Custom)表服务接口
 *
 * @author makejava
 * @since 2022-02-26 21:12:25
 */
public interface CustomService {

    /**
     * 通过ID查询单条数据
     *
     * @param customId 主键
     * @return 实例对象
     */
    Custom queryById(String customId);

    //查询所有
    List<Custom> queryAll();

    /**
     * 分页查询
     *
     * @param custom      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Custom> queryByPage(Custom custom, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param custom 实例对象
     * @return 实例对象
     */
    Custom insert(Custom custom);

    /**
     * 修改数据
     *
     * @param custom 实例对象
     * @return 实例对象
     */
    Custom update(Custom custom);

    /**
     * 通过主键删除数据
     *
     * @param customId 主键
     * @return 是否成功
     */
    boolean deleteById(String customId);

}

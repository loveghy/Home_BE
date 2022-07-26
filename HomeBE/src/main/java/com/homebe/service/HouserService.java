package com.homebe.service;

import com.homebe.entity.Houser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Houser)表服务接口
 *
 * @author makejava
 * @since 2022-02-26 21:11:59
 */
public interface HouserService {

    /**
     * 通过ID查询单条数据
     *
     * @param houserId 主键
     * @return 实例对象
     */
    Houser queryById(String houserId);

    //查询所有
    List<Houser> queryAll();

    /**
     * 分页查询
     *
     * @param houser      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Houser> queryByPage(Houser houser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param houser 实例对象
     * @return 实例对象
     */
    Houser insert(Houser houser);

    /**
     * 修改数据
     *
     * @param houser 实例对象
     * @return 实例对象
     */
    Houser update(Houser houser);

    /**
     * 通过主键删除数据
     *
     * @param houserId 主键
     * @return 是否成功
     */
    boolean deleteById(String houserId);

}

package com.homebe.service;

import com.homebe.entity.Housetype;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Housetype)表服务接口
 *
 * @author makejava
 * @since 2022-03-14 22:18:56
 */
public interface HousetypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Housetype queryById(Integer id);

    //查询所有
    List<Housetype> queryAll();

    /**
     * 分页查询
     *
     * @param housetype   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Housetype> queryByPage(Housetype housetype, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param housetype 实例对象
     * @return 实例对象
     */
    Housetype insert(Housetype housetype);

    /**
     * 修改数据
     *
     * @param housetype 实例对象
     * @return 实例对象
     */
    Housetype update(Housetype housetype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}

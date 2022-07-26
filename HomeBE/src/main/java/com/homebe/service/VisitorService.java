package com.homebe.service;

import com.homebe.entity.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Visitor)表服务接口
 *
 * @author makejava
 * @since 2022-03-23 22:35:38
 */
public interface VisitorService {

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    Visitor queryById(String username);

    //查询所有
    List<Visitor> queryAll();

    /**
     * 分页查询
     *
     * @param visitor     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Visitor> queryByPage(Visitor visitor, PageRequest pageRequest);

    String Login(Visitor visitor);

    /**
     * 新增数据
     *
     * @param visitor 实例对象
     * @return 实例对象
     */
    Visitor insert(Visitor visitor);

    /**
     * 修改数据
     *
     * @param visitor 实例对象
     * @return 实例对象
     */
    Visitor update(Visitor visitor);

    /**
     * 通过主键删除数据
     *
     * @param username 主键
     * @return 是否成功
     */
    boolean deleteById(String username);

}

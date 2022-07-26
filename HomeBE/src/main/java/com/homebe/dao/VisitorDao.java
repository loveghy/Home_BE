package com.homebe.dao;

import com.homebe.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Visitor)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-23 22:35:38
 */
@Mapper
public interface VisitorDao {

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
     * 查询指定行数据
     *
     * @param visitor  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Visitor> queryAllByLimit(Visitor visitor, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param visitor 查询条件
     * @return 总行数
     */
    long count(Visitor visitor);

    /**
     * 新增数据
     *
     * @param visitor 实例对象
     * @return 影响行数
     */
    int insert(Visitor visitor);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Visitor> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Visitor> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Visitor> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Visitor> entities);

    /**
     * 修改数据
     *
     * @param visitor 实例对象
     * @return 影响行数
     */
    int update(Visitor visitor);

    /**
     * 通过主键删除数据
     *
     * @param username 主键
     * @return 影响行数
     */
    int deleteById(String username);

}


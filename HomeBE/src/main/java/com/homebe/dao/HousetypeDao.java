package com.homebe.dao;

import com.homebe.entity.Housetype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Housetype)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-14 22:18:56
 */
@Mapper
public interface HousetypeDao {

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
     * 查询指定行数据
     *
     * @param housetype 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<Housetype> queryAllByLimit(Housetype housetype, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param housetype 查询条件
     * @return 总行数
     */
    long count(Housetype housetype);

    /**
     * 新增数据
     *
     * @param housetype 实例对象
     * @return 影响行数
     */
    int insert(Housetype housetype);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Housetype> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Housetype> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Housetype> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Housetype> entities);

    /**
     * 修改数据
     *
     * @param housetype 实例对象
     * @return 影响行数
     */
    int update(Housetype housetype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}


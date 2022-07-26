package com.homebe.dao;

import com.homebe.entity.Houser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Houser)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-26 21:11:59
 */
@Mapper
public interface HouserDao {

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
     * 查询指定行数据
     *
     * @param houser   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Houser> queryAllByLimit(Houser houser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param houser 查询条件
     * @return 总行数
     */
    long count(Houser houser);

    /**
     * 新增数据
     *
     * @param houser 实例对象
     * @return 影响行数
     */
    int insert(Houser houser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Houser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Houser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Houser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Houser> entities);

    /**
     * 修改数据
     *
     * @param houser 实例对象
     * @return 影响行数
     */
    int update(Houser houser);

    /**
     * 通过主键删除数据
     *
     * @param houserId 主键
     * @return 影响行数
     */
    int deleteById(String houserId);

}


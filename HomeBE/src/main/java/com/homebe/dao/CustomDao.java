package com.homebe.dao;

import com.homebe.entity.Custom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Custom)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-26 21:12:25
 */
@Mapper
public interface CustomDao {

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
     * 查询指定行数据
     *
     * @param custom   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Custom> queryAllByLimit(Custom custom, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param custom 查询条件
     * @return 总行数
     */
    long count(Custom custom);

    /**
     * 新增数据
     *
     * @param custom 实例对象
     * @return 影响行数
     */
    int insert(Custom custom);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Custom> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Custom> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Custom> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Custom> entities);

    /**
     * 修改数据
     *
     * @param custom 实例对象
     * @return 影响行数
     */
    int update(Custom custom);

    /**
     * 通过主键删除数据
     *
     * @param customId 主键
     * @return 影响行数
     */
    int deleteById(String customId);

}


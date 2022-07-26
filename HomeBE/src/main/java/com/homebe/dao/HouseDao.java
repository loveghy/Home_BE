package com.homebe.dao;

import com.homebe.entity.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (House)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-14 20:25:58
 */
@Mapper
public interface HouseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param houseId 主键
     * @return 实例对象
     */
    House queryById(String houseId);

   //查询个人房源
    List<House> queryBycontact(String houseContact);
    //查询所有
    List<House> queryAll();

    /**
     * 查询指定行数据
     *
     * @param house    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<House> queryAllByLimit(House house, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param house 查询条件
     * @return 总行数
     */
    long count(House house);

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 影响行数
     */
    int insert(House house);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<House> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<House> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<House> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<House> entities);

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 影响行数
     */
    int update(House house);

    /**
     * 通过主键删除数据
     *
     * @param houseId 主键
     * @return 影响行数
     */
    int deleteById(String houseId);

}


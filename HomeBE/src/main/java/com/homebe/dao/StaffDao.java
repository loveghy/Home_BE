package com.homebe.dao;

import com.homebe.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Staff)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-10 21:03:43
 */
@Mapper
public interface StaffDao {

    /**
     * 通过ID查询单条数据
     *
     * @param staffId 主键
     * @return 实例对象
     */
    Staff queryById(String staffId);

    String queryPassword(String staffId);
    //查询所有
    List<Staff> queryAll();

    /**
     * 查询指定行数据
     *
     * @param staff    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Staff> queryAllByLimit(Staff staff, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param staff 查询条件
     * @return 总行数
     */
    long count(Staff staff);

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 影响行数
     */
    int insert(Staff staff);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Staff> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Staff> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Staff> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Staff> entities);

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 影响行数
     */
    int update(Staff staff);

    /**
     * 通过主键删除数据
     *
     * @param staffId 主键
     * @return 影响行数
     */
    int deleteById(String staffId);

}


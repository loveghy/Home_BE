package com.homebe.dao;

import com.homebe.entity.House;
import com.homebe.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-25 00:36:26
 */
@Mapper
public interface MessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param messageId 主键
     * @return 实例对象
     */
    Message queryById(Integer messageId);
    List<Message> queryBy(@Param("houseId") String houseId,@Param("userId") String userId);
    //查询所有
    List<Message> queryAll();
    /**
     * 查询指定行数据
     *
     * @param message  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Message> queryAllByLimit(Message message, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param message 查询条件
     * @return 总行数
     */
    long count(Message message);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Message> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Message> entities);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param messageId 主键
     * @return 影响行数
     */
    int deleteById(Integer messageId);

}


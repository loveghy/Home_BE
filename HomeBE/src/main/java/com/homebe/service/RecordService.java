package com.homebe.service;

import com.homebe.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Record)表服务接口
 *
 * @author makejava
 * @since 2022-04-01 11:58:51
 */
public interface RecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    Record queryById(String recordId);

    //查询所有
    List<Record> queryAll();

    /**
     * 分页查询
     *
     * @param record      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Record> queryByPage(Record record, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param record 实例对象
     * @return 实例对象
     */
    Record insert(Record record);

    /**
     * 修改数据
     *
     * @param record 实例对象
     * @return 实例对象
     */
    Record update(Record record);

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 是否成功
     */
    boolean deleteById(String recordId);

}

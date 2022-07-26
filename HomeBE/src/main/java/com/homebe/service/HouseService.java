package com.homebe.service;

import com.homebe.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (House)表服务接口
 *
 * @author makejava
 * @since 2022-03-14 20:25:58
 */
public interface HouseService {
    /**
     * 通过ID查询单条数据
     *
     * @param houseId 主键
     * @return 实例对象
     */
    House queryById(String houseId);

    List<House> queryBycontact(String houseContact);

    //查询所有
    List<House> queryAll();

    String upload(House house, MultipartFile fileUpload);

    /**
     * 分页查询
     *
     * @param house       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<House> queryByPage(House house, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    House insert(House house);

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    House update(House house);

    /**
     * 通过主键删除数据
     *
     * @param houseId 主键
     * @return 是否成功
     */
    boolean deleteById(String houseId);

}

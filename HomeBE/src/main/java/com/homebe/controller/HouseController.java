package com.homebe.controller;

import com.homebe.entity.House;
import com.homebe.service.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (House)表控制层
 *
 * @author makejava
 * @since 2022-03-14 20:25:58
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("house")
public class HouseController {
    /**
     * 服务对象
     */
    @Resource
    private HouseService houseService;

    /**
     * 分页查询
     *
     * @param house       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<House>> queryByPage(House house, PageRequest pageRequest) {
        return ResponseEntity.ok(this.houseService.queryByPage(house, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<House> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.houseService.queryById(id));
    }

    //查询个人房源
    @GetMapping("selecId/{Id}")
    public  ResponseEntity<List<House>> queryBycontact(@PathVariable("Id") String Id){
        System.out.println(Id);
        return ResponseEntity.ok(this.houseService.queryBycontact(Id));
    }
    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<House>> queryAll() {
        return ResponseEntity.ok(this.houseService.queryAll());
    }

    @PostMapping(value = "/upload",produces = "text/html; charset=UTF-8")
    public String upload(House house, MultipartFile file){
        return houseService.upload(house,file);
    }
    /**
     * 新增数据
     *
     * @param house 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<House> add(House house) {
        return ResponseEntity.ok(this.houseService.insert(house));
    }

    /**
     * 编辑数据
     *
     * @param house 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<House> edit(House house) {
        return ResponseEntity.ok(this.houseService.update(house));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.houseService.deleteById(id));
    }

}


package com.homebe.controller;

import com.homebe.entity.Housetype;
import com.homebe.service.HousetypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Housetype)表控制层
 *
 * @author makejava
 * @since 2022-03-14 22:18:56
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("housetype")
public class HousetypeController {
    /**
     * 服务对象
     */
    @Resource
    private HousetypeService housetypeService;

    /**
     * 分页查询
     *
     * @param housetype   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Housetype>> queryByPage(Housetype housetype, PageRequest pageRequest) {
        return ResponseEntity.ok(this.housetypeService.queryByPage(housetype, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Housetype> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.housetypeService.queryById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Housetype>> queryAll() {
        return ResponseEntity.ok(this.housetypeService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param housetype 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Housetype> add(Housetype housetype) {
        return ResponseEntity.ok(this.housetypeService.insert(housetype));
    }

    /**
     * 编辑数据
     *
     * @param housetype 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Housetype> edit(Housetype housetype) {
        return ResponseEntity.ok(this.housetypeService.update(housetype));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.housetypeService.deleteById(id));
    }

}


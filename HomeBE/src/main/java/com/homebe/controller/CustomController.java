package com.homebe.controller;

import com.homebe.entity.Custom;
import com.homebe.service.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Custom)表控制层
 *
 * @author makejava
 * @since 2022-02-26 21:12:25
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("custom")
public class CustomController {
    /**
     * 服务对象
     */
    @Resource
    private CustomService customService;

    /**
     * 分页查询
     *
     * @param custom      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Custom>> queryByPage(Custom custom, PageRequest pageRequest) {
        return ResponseEntity.ok(this.customService.queryByPage(custom, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Custom> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.customService.queryById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Custom>> queryAll() {
        return ResponseEntity.ok(this.customService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param custom 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Custom> add(Custom custom) {
        return ResponseEntity.ok(this.customService.insert(custom));
    }

    /**
     * 编辑数据
     *
     * @param custom 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Custom> edit(Custom custom) {
        return ResponseEntity.ok(this.customService.update(custom));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.customService.deleteById(id));
    }

}


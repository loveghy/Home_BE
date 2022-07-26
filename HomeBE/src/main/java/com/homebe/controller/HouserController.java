package com.homebe.controller;

import com.homebe.entity.Houser;
import com.homebe.service.HouserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Houser)表控制层
 *
 * @author makejava
 * @since 2022-02-26 21:11:59
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("houser")
public class HouserController {
    /**
     * 服务对象
     */
    @Resource
    private HouserService houserService;

    /**
     * 分页查询
     *
     * @param houser      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Houser>> queryByPage(Houser houser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.houserService.queryByPage(houser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Houser> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.houserService.queryById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Houser>> queryAll() {
        return ResponseEntity.ok(this.houserService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param houser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Houser> add(Houser houser) {
        return ResponseEntity.ok(this.houserService.insert(houser));
    }

    /**
     * 编辑数据
     *
     * @param houser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Houser> edit(Houser houser) {
        return ResponseEntity.ok(this.houserService.update(houser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.houserService.deleteById(id));
    }

}


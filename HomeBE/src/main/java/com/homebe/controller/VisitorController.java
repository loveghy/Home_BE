package com.homebe.controller;

import com.homebe.entity.Visitor;
import com.homebe.service.VisitorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Visitor)表控制层
 *
 * @author makejava
 * @since 2022-03-23 22:35:38
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("visitor")
public class VisitorController {
    /**
     * 服务对象
     */
    @Resource
    private VisitorService visitorService;

    /**
     * 分页查询
     *
     * @param visitor     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Visitor>> queryByPage(Visitor visitor, PageRequest pageRequest) {
        return ResponseEntity.ok(this.visitorService.queryByPage(visitor, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Visitor> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.visitorService.queryById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Visitor>> queryAll() {
        return ResponseEntity.ok(this.visitorService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param visitor 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Visitor> add(Visitor visitor) {
        return ResponseEntity.ok(this.visitorService.insert(visitor));
    }

    /**
     * 登录
     *
     * @param visitor 实体
     * @return 判断结果
     */
    @PostMapping("/login")
    public String Login(Visitor visitor) {
        return visitorService.Login(visitor);
    }

    /**
     * 编辑数据
     *
     * @param visitor 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Visitor> edit(Visitor visitor) {
        return ResponseEntity.ok(this.visitorService.update(visitor));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.visitorService.deleteById(id));
    }

}


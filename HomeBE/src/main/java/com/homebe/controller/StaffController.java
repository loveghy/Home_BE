package com.homebe.controller;

import com.homebe.entity.Staff;
import com.homebe.service.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2022-03-10 21:03:43
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("staff")
public class StaffController {
    /**
     * 服务对象
     */
    @Resource
    private StaffService staffService;

    /**
     * 分页查询
     *
     * @param staff       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Staff>> queryByPage(Staff staff, PageRequest pageRequest) {
        return ResponseEntity.ok(this.staffService.queryByPage(staff, pageRequest));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Staff user) {
        return staffService.selectUserName(user);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Staff> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.staffService.queryById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Staff>> queryAll() {
        return ResponseEntity.ok(this.staffService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param staff 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Staff> add(Staff staff) {
        return ResponseEntity.ok(this.staffService.insert(staff));
    }

    /**
     * 编辑数据
     *
     * @param staff 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Staff> edit(Staff staff) {
        return ResponseEntity.ok(this.staffService.update(staff));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.staffService.deleteById(id));
    }

}


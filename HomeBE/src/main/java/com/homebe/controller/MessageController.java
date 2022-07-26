package com.homebe.controller;

import com.homebe.entity.Message;
import com.homebe.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2022-03-25 00:36:26
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 分页查询
     *
     * @param message     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Message>> queryByPage(Message message, PageRequest pageRequest) {
        return ResponseEntity.ok(this.messageService.queryByPage(message, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Message> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.messageService.queryById(id));
    }
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public List<Message> login(@RequestBody Message message) {
       /* String houseId=message.getHouseId();
        String userId=message.getUserId();
        System.out.println(houseId);*/
        return messageService.queryBy(message);
    }
    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Message>> queryAll() {
        return ResponseEntity.ok(this.messageService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param message 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Message> add(Message message) {
        return ResponseEntity.ok(this.messageService.insert(message));
    }

    /**
     * 编辑数据
     *
     * @param message 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Message> edit(Message message) {
        return ResponseEntity.ok(this.messageService.update(message));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.messageService.deleteById(id));
    }

}


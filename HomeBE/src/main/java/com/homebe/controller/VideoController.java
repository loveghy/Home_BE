package com.homebe.controller;

import com.homebe.entity.House;
import com.homebe.entity.Video;
import com.homebe.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Video)表控制层
 *
 * @author makejava
 * @since 2022-04-22 12:29:50
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("video")
public class VideoController {
    /**
     * 服务对象
     */
    @Resource
    private VideoService videoService;

    /**
     * 分页查询
     *
     * @param video       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Video>> queryByPage(Video video, PageRequest pageRequest) {
        return ResponseEntity.ok(this.videoService.queryByPage(video, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Video> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.videoService.queryById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping("All")
    public ResponseEntity<List<Video>> queryAll() {
        return ResponseEntity.ok(this.videoService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param video 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Video> add(Video video) {
        return ResponseEntity.ok(this.videoService.insert(video));
    }


    @PostMapping(value = "/upload",produces = "text/html; charset=UTF-8")
    public String upload(Video video, MultipartFile file){
        return videoService.upload(video,file);
    }
    /**
     * 编辑数据
     *
     * @param video 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Video> edit(Video video) {
        return ResponseEntity.ok(this.videoService.update(video));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.videoService.deleteById(id));
    }

}


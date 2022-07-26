package com.homebe.service.impl;

import com.homebe.entity.House;
import com.homebe.entity.Video;
import com.homebe.dao.VideoDao;
import com.homebe.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * (Video)表服务实现类
 *
 * @author makejava
 * @since 2022-04-22 12:29:51
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoDao videoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Video queryById(String id) {
        return this.videoDao.queryById(id);
    }

    //查询所有
    public List<Video> queryAll() {
        return this.videoDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param video       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Video> queryByPage(Video video, PageRequest pageRequest) {
        long total = this.videoDao.count(video);
        return new PageImpl<>(this.videoDao.queryAllByLimit(video, pageRequest), pageRequest, total);
    }

    @Override
    public String upload(Video video, MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        System.out.println(fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        System.out.println(suffixName);
        String filename=video.getId();
        fileName = filename+suffixName;
        String path = "../../../../static/upload/video/"+fileName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        video.setUrl(path);
        videoDao.insert(video);
        String filePath = "D:\\Item\\HomeFE\\static\\upload\\video\\";
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath+fileName));
            //返回提示信息
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video insert(Video video) {
        this.videoDao.insert(video);
        return video;
    }

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video update(Video video) {
        this.videoDao.update(video);
        return this.queryById(video.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.videoDao.deleteById(id) > 0;
    }
}

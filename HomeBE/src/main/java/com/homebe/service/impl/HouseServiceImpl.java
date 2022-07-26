package com.homebe.service.impl;

import com.homebe.dao.HouseDao;
import com.homebe.entity.House;
import com.homebe.service.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2022-03-14 20:25:58
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param houseId 主键
     * @return 实例对象
     */
    @Override
    public House queryById(String houseId) {
        return this.houseDao.queryById(houseId);
    }

    @Override
    public List<House> queryBycontact(String houseContact){
        return this.houseDao.queryBycontact(houseContact);
    }


    //查询所有
    public List<House> queryAll() {
        return this.houseDao.queryAll();
    }

    //房源上传
    @Override
    public String upload(House house, MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        System.out.println(fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        System.out.println(suffixName);
        String filename=house.getHouseId();
        fileName = filename+suffixName;
        String path = "../../../../static/upload/image/"+fileName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        house.setHousePicture(path);
        houseDao.insert(house);
        String filePath = "D:\\Item\\HomeFE\\static\\upload\\image\\";
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            //返回提示信息
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    /**
     * 分页查询
     *
     * @param house       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<House> queryByPage(House house, PageRequest pageRequest) {
        long total = this.houseDao.count(house);
        return new PageImpl<>(this.houseDao.queryAllByLimit(house, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House insert(House house) {
        this.houseDao.insert(house);
        return house;
    }

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House update(House house) {
        this.houseDao.update(house);
        return this.queryById(house.getHouseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param houseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String houseId) {
        String suffixName = houseDao.queryById(houseId).getHousePicture().substring(houseDao.queryById(houseId).getHousePicture().lastIndexOf("."));
        String filename=houseId+suffixName;
        String path = "D:\\Item\\HomeFE\\static\\upload\\image\\"+filename;
        File file = new File(path);
        System.out.println("这是文件"+file);
        System.out.println(file.exists());
        if (file.exists()) {//文件是否存在
            if (file.delete()) {//存在就删了，返回1
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("文件不存在");
        }
        return this.houseDao.deleteById(houseId) > 0;
    }
}

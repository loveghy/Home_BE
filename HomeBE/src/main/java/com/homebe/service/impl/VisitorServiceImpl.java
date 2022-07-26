package com.homebe.service.impl;

import com.homebe.dao.VisitorDao;
import com.homebe.entity.Visitor;
import com.homebe.service.VisitorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Visitor)表服务实现类
 *
 * @author makejava
 * @since 2022-03-23 22:35:38
 */
@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
    @Resource
    private VisitorDao visitorDao;

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    @Override
    public Visitor queryById(String username) {
        return this.visitorDao.queryById(username);
    }

    //查询所有
    public List<Visitor> queryAll() {
        return this.visitorDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param visitor     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Visitor> queryByPage(Visitor visitor, PageRequest pageRequest) {
        long total = this.visitorDao.count(visitor);
        return new PageImpl<>(this.visitorDao.queryAllByLimit(visitor, pageRequest), pageRequest, total);
    }
    /**
     * 新增数据
     *
     * @param visitor 对象
     * @return 字符
     */
    @Override
    public  String Login(Visitor visitor){
        System.out.println(visitor.getUsername());
        System.out.println(visitor.getPassword());
        String result="-1";
        if (visitorDao.queryById(visitor.getUsername())==null){
            result ="0";
        }else if (!visitorDao.queryById(visitor.getUsername()).getPassword().equals(visitor.getPassword())){
            result = "1";
        }else if (visitorDao.queryById(visitor.getUsername()).getPassword().equals(visitor.getPassword())){
            result = "2";
        }
        return result;
    }
    /**
     * 新增数据
     *
     * @param visitor 实例对象
     * @return 实例对象
     */
    @Override
    public Visitor insert(Visitor visitor) {
        if (visitorDao.queryById(visitor.getUsername())!=null){
           return null;
        }
        else{
            this.visitorDao.insert(visitor);
            return visitor;
        }
    }

    /**
     * 修改数据
     *
     * @param visitor 实例对象
     * @return 实例对象
     */
    @Override
    public Visitor update(Visitor visitor) {
        this.visitorDao.update(visitor);
        return this.queryById(visitor.getUsername());
    }

    /**
     * 通过主键删除数据
     *
     * @param username 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String username) {
        return this.visitorDao.deleteById(username) > 0;
    }
}

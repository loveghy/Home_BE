package com.homebe.service.impl;

import com.homebe.dao.StaffDao;
import com.homebe.entity.Staff;
import com.homebe.service.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Staff)表服务实现类
 *
 * @author makejava
 * @since 2022-03-10 21:03:43
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;

    /**
     * 通过ID查询单条数据
     *
     * @param staffId 主键
     * @return 实例对象
     */
    @Override
    public Staff queryById(String staffId) {
        return this.staffDao.queryById(staffId);
    }

    @Override
    public String selectUserName(Staff user) {
        String staffId = user.getStaffId();
        String password = user.getPassword();
        String staffRoles = user.getStaffRoles();
        System.out.println("账号"+staffId);
        System.out.println("密码"+password);
        System.out.println("这是角色"+staffRoles);
        String result = "-1";
        // 用户不存在
        /*userDao.queryByPassword(username)*/
        if (staffDao.queryById(staffId) == null) {
            result = "0";
            //  用户存在，但密码输入错误
        }else if(!staffDao.queryById(staffId).getPassword().equals(password) ){
            result = "1";
            //  密码正确
        }else if(staffDao.queryById(staffId).getPassword().equals(password)) {
            //  确定角色
           if (staffRoles.equals(staffDao.queryById(staffId).getStaffRoles())) {
               if (staffRoles.equals("1")){
                   result = "2";
               }else if (staffRoles.equals("0")){
                   result = "3";
               }
            }else{
               result = "-2";
           }
        }
        System.out.println(result);
        return result;
    }
    //查询所有
    public List<Staff> queryAll() {
        return this.staffDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param staff       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Staff> queryByPage(Staff staff, PageRequest pageRequest) {
        long total = this.staffDao.count(staff);
        return new PageImpl<>(this.staffDao.queryAllByLimit(staff, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff insert(Staff staff) {
        this.staffDao.insert(staff);
        return staff;
    }

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff update(Staff staff) {
        this.staffDao.update(staff);
        return this.queryById(staff.getStaffId());
    }

    /**
     * 通过主键删除数据
     *
     * @param staffId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String staffId) {
        return this.staffDao.deleteById(staffId) > 0;
    }
}

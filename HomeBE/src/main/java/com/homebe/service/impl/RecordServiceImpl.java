package com.homebe.service.impl;

import com.homebe.dao.HouseDao;
import com.homebe.entity.House;
import com.homebe.entity.Record;
import com.homebe.dao.RecordDao;
import com.homebe.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Record)表服务实现类
 *
 * @author makejava
 * @since 2022-04-01 11:58:51
 */
@Service("recordService")
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordDao recordDao;

    @Resource
    private HouseDao houseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    @Override
    public Record queryById(String recordId) {
        return this.recordDao.queryById(recordId);
    }

    //查询所有
    public List<Record> queryAll() {
        return this.recordDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param record      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Record> queryByPage(Record record, PageRequest pageRequest) {
        long total = this.recordDao.count(record);
        return new PageImpl<>(this.recordDao.queryAllByLimit(record, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param record 实例对象
     * @return 实例对象
     */
    @Override
    public Record insert(Record record) {
        String houseId= record.getHouseId();
        Double agencyFee = null;
        if (record.getRecordType().equals("售房")){
            agencyFee=(houseDao.queryById(houseId).getHousePay()*2/100);//售房中介费
        }else if (record.getRecordType().equals("租房")){
            agencyFee=(houseDao.queryById(houseId).getHousePay()*20/100);//租房中介费
        }
        record.setAgencyFee(agencyFee);
        Double recordPay= houseDao.queryById(houseId).getHousePay()+record.getDeposit()+agencyFee;
        record.setRecordPay(recordPay);
        this.recordDao.insert(record);
        System.out.println(recordPay);
        return record;
    }

    /**
     * 修改数据
     *
     * @param record 实例对象
     * @return 实例对象
     */
    @Override
    public Record update(Record record) {
        House house = new House();
        String ID=record.getHouseId();
        if (record.getRecordState().equals("已支付")){
            house.setHouseId(ID);
            if (record.getRecordType().equals("售房")){
                house.setHouseState("已售");
            }else if (record.getRecordType().equals("租房")){
                house.setHouseState("已租");
            }
            houseDao.update(house);
        }
        this.recordDao.update(record);
        return this.queryById(record.getRecordId());
    }

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String recordId) {
        House house=new House();
        house.setHouseId(recordDao.queryById(recordId).getHouseId());
        if (recordDao.queryById(recordId).getRecordType().equals("售房")){
            house.setHouseState("未售");
        }else if (recordDao.queryById(recordId).getRecordType().equals("租房")){
            house.setHouseState("未租");
        }
        houseDao.update(house);
        return this.recordDao.deleteById(recordId) > 0;
    }
}

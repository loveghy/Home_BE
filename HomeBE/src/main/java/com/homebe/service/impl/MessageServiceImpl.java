package com.homebe.service.impl;

import com.homebe.dao.MessageDao;
import com.homebe.entity.Message;
import com.homebe.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2022-03-25 00:36:26
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param messageId 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(Integer messageId) {
        return this.messageDao.queryById(messageId);
    }
    @Override
    public List<Message> queryBy(Message message){
        return this.messageDao.queryBy(message.getHouseId(), message.getUserId());
    }
    //查询所有
    public List<Message> queryAll() {
        return this.messageDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param message     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Message> queryByPage(Message message, PageRequest pageRequest) {
        long total = this.messageDao.count(message);
        return new PageImpl<>(this.messageDao.queryAllByLimit(message, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message insert(Message message) {
        this.messageDao.insert(message);
        return message;
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update(Message message) {
        this.messageDao.update(message);
        return this.queryById(message.getMessageId());
    }

    /**
     * 通过主键删除数据
     *
     * @param messageId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer messageId) {
        return this.messageDao.deleteById(messageId) > 0;
    }
}

/**
 * @Author Mr.k
 * @Description // Ecomm实体类的service层接口的实现类
 * @Date 17:16 2019/4/24
 **/

package edu.uni.userBaseInfo1.service.impl;

import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.mapper.EcommMapper;
import edu.uni.userBaseInfo1.service.EcommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcommServiceImpl implements EcommService {

    @Autowired
    private EcommMapper ecommMapper;

    @Override
    public List<Ecomm> selectAll() {
        return ecommMapper.selectByExample(null);
    }

    @Override
    public Ecomm selectById(long id) {
        return ecommMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insert(Ecomm ecomm) {
        return ecommMapper.insertSelective(ecomm) > 0 ? true : false;
    }

    @Override
    public boolean update(Ecomm ecomm) {
        return ecommMapper.updateByPrimaryKeySelective(ecomm) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        return ecommMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}

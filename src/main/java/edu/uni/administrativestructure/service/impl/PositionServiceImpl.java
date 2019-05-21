package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.bean.PositionExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.PositionMapper;
import edu.uni.administrativestructure.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.9
 * 功能：岗位信息实现类
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(Position position) {
        return positionMapper.insert(position) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        //获取旧数据
        Position position = positionMapper.selectByPrimaryKey(id);
        position.setDeleted(true);
        //删除记录
        if(positionMapper.deleteByPrimaryKey(id)<=0){
            return false;
        }
        //删除后插入旧的记录
        if(positionMapper.insert(position)<=0){
            return false;
        }
        else
            return true;
    }

    @Override
    public boolean update(Position position) {
        //获取修改前的数据
        long id = position.getId();
        Position position1 = positionMapper.selectByPrimaryKey(id);
        position1.setDeleted(true);
        //修改数据
        if(positionMapper.updateByPrimaryKeySelective(position)<=0){
            return false;
        }
        //修改后插入旧的记录
        if(positionMapper.insert(position1)<=0){
            return false;
        }
        else
            return true;
    }

    @Override
    public Position select(long id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Position> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        PositionExample example = new PositionExample();
        PositionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        List<Position> positions = positionMapper.selectByExample(example);
        if(positions != null)
            return new PageInfo<>(positions);
        else
            return null;
    }

    @Override
    public List<Position> selectAll() {
        // 创建查询条件
        PositionExample example = new PositionExample();
        PositionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return positionMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Position> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        PositionExample example = new PositionExample();
        PositionExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Position> positions = positionMapper.selectByExample(example);
        if(positions != null)
            return new PageInfo<>(positions);
        else
            return null;
    }
}

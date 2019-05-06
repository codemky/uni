package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.EmployeeHistory;
import edu.uni.userBaseInfo1.mapper.EmployeeHistoryMapper;
import edu.uni.userBaseInfo1.service.EmployeeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description employeeHistory实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class EmployeeHistoryServiceImpl implements EmployeeHistoryService {
    //持久层接口的对象
    @Autowired
    private EmployeeHistoryMapper employeeHistoryMapper;//爆红的话编译器的原因，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<EmployeeHistory>
     * @apiNote: 查询所有的简历信息
     */
    @Override
    public List<EmployeeHistory> selectAll() {
        return employeeHistoryMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return EmployeeHistory
     * @apiNote: 根据id查询出一条简历信息
     */
    @Override
    public EmployeeHistory selectById(Long id) {
        return employeeHistoryMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<EmployeeHistory>
     * @apiNote: 分页查询出所有简历信息
     */
    @Override
    public PageInfo<EmployeeHistory> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<EmployeeHistory> employeeHistorys = employeeHistoryMapper.selectByExample(null);

        if (employeeHistorys!=null){
            return new PageInfo<>(employeeHistorys);
        }
        return null;
    }

    /**
     * Author: laizhouhao 18:21 2019/5/6
     * @param user_id
     * @return List<EmployeeHistory>
     * @apiNote: 根据用户id查询简历信息
     */
    @Override
    public List<EmployeeHistory> selectByUserId(Long user_id) {
        return employeeHistoryMapper.selectByUserId(user_id);
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param employeeHistory
     * @return boolean
     * @apiNote: 增加EmployeeHistory表的一个记录
     */
    @Override
    public boolean insert(EmployeeHistory employeeHistory) {
        return employeeHistoryMapper.insert(employeeHistory)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param employeeHistory
     * @return boolean
     * @apiNote: 用一个新的对象更新EmployeeHistory表的一个记录
     */
    @Override
    public boolean update(EmployeeHistory employeeHistory) {
        return employeeHistoryMapper.updateByPrimaryKey(employeeHistory) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除EmployeeHistory表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return employeeHistoryMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}

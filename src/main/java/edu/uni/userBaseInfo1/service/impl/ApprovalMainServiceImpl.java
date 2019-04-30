package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.ApprovalMain;
import edu.uni.userBaseInfo1.mapper.ApprovalMainMapper;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description ApprovalMain实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class ApprovalMainServiceImpl implements ApprovalMainService {
    //持久层接口的对象
    @Autowired
    private ApprovalMainMapper approvalMainMapper;//爆红的话编译器的原因，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<ApprovalMain>
     * @apiNote: 查询所有的每一种申请的审批步骤数
     */
    @Override
    public List<ApprovalMain> selectAll() {
        return approvalMainMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return ApprovalMain
     * @apiNote: 根据id查询出一条每一种申请的审批步骤数信息
     */
    @Override
    public ApprovalMain selectById(Long id) {
        return approvalMainMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<ApprovalMain>
     * @apiNote: 分页查询出所有每一种申请的审批步骤数信息
     */
    @Override
    public PageInfo<ApprovalMain> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<ApprovalMain> approvalMains = approvalMainMapper.selectByExample(null);

        if (approvalMains!=null){
            return new PageInfo<>(approvalMains);
        }
        return null;
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param approvalMain
     * @return boolean
     * @apiNote: 增加approvalMain表的一个记录
     */
    @Override
    public boolean insert(ApprovalMain approvalMain) {
        return approvalMainMapper.insert(approvalMain)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param approvalMain
     * @return boolean
     * @apiNote: 用一个新的对象更新approvalMain表的一个记录
     */
    @Override
    public boolean update(ApprovalMain approvalMain) {
        return approvalMainMapper.updateByPrimaryKey(approvalMain) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除approvalMain表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return approvalMainMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}

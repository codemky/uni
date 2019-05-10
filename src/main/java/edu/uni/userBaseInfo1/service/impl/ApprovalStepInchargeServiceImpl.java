package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.ApprovalMain;
import edu.uni.userBaseInfo1.bean.ApprovalStepIncharge;
import edu.uni.userBaseInfo1.bean.ApprovalStepInchargeExample;
import edu.uni.userBaseInfo1.mapper.ApprovalMainMapper;
import edu.uni.userBaseInfo1.mapper.ApprovalStepInchargeMapper;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import edu.uni.userBaseInfo1.service.ApprovalStepInchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description ApprovalStepIncharge实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class ApprovalStepInchargeServiceImpl implements ApprovalStepInchargeService {
    //持久层接口的对象
    @Autowired
    private ApprovalStepInchargeMapper approvalStepInchargeMapper;//爆红的话编译器的原因，不影响运行
    @Autowired
    private ApprovalMainService approvalMainService;

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 21:28 2019/5/7
     * @param id
     * @return List
     * @apiNote: 根据审批规定表id查询该规定的每一步审批的具体情况
     */
    @Override
    public List<ApprovalStepIncharge> selectByMainId(Long id) {
        ApprovalStepInchargeExample example = new ApprovalStepInchargeExample();
        ApprovalStepInchargeExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("step ASC");
        criteria.andApprovalMainIdEqualTo(id);
        criteria.andDeletedEqualTo(false);

        List<ApprovalStepIncharge> approvalStepIncharges =
                approvalStepInchargeMapper.selectByExample(example);

        return approvalStepIncharges;
    }

    /**
     * Author: mokuanyuan 15:40 2019/5/8
     * @param mainId
     * @param step
     * @return role_id
     * @apiNote: 根据审批规定表approval_main_id和具体部署编号查询具体某一步的角色id
     */
    @Override
    public Long selectRoleIdByMainIdAndStep(Long mainId, Integer step) {
        ApprovalStepInchargeExample example = new ApprovalStepInchargeExample();
        ApprovalStepInchargeExample.Criteria criteria = example.createCriteria();
        criteria.andApprovalMainIdEqualTo(mainId);
        criteria.andStepEqualTo(step);
        criteria.andDeletedEqualTo(false);

        List<ApprovalStepIncharge> approvalStepIncharges =
                approvalStepInchargeMapper.selectByExample(example);
        if(approvalStepIncharges.size() == 1) {
            return approvalStepIncharges.get(0).getRoleId();
        }
        else{
            return (long)-1;
        }

    }

    /**
     * Author: mokuanyuan 16:01 2019/5/8
     * @param id
     * @return boolean
     * @apiNote: 把某个步骤记录逻辑删除（Deleted字段置为true）
     */
    @Override
    public boolean updateToInvalidById(Long id) {
        ApprovalStepIncharge approvalStepIncharge = approvalStepInchargeMapper.selectByPrimaryKey(id);
        approvalStepIncharge.setDeleted(true);
        ApprovalMain approvalMain = approvalMainService.
                selectById(approvalStepIncharge.getApprovalMainId());
        approvalMain.setStepCnt(approvalMain.getStepCnt()-1);
        approvalMainService.update(approvalMain);

        return approvalStepInchargeMapper.updateByPrimaryKey(approvalStepIncharge) > 0 ? true : false;

    }

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<ApprovalStepIncharge>
     * @apiNote: 查询所有的每一种申请的每一个审批步骤的角色
     */
    @Override
    public List<ApprovalStepIncharge> selectAll() {
        return approvalStepInchargeMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return ApprovalStepIncharge
     * @apiNote: 根据id查询出一条每一种申请的每一个审批步骤的角色信息
     */
    @Override
    public ApprovalStepIncharge selectById(Long id) {
        return approvalStepInchargeMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<ApprovalStepIncharge>
     * @apiNote: 分页查询出所有每一种申请的每一个审批步骤的角色信息
     */
    @Override
    public PageInfo<ApprovalStepIncharge> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<ApprovalStepIncharge> approvalStepIncharges = approvalStepInchargeMapper.selectByExample(null);

        if (approvalStepIncharges!=null){
            return new PageInfo<>(approvalStepIncharges);
        }
        return null;
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param approvalStepIncharge
     * @return boolean
     * @apiNote: 增加ApprovalStepIncharge表的一个记录
     */
    @Override
    public boolean insert(ApprovalStepIncharge approvalStepIncharge) {
        ApprovalMain approvalMain = approvalMainService.
                selectById(approvalStepIncharge.getApprovalMainId());
        approvalMain.setStepCnt(approvalMain.getStepCnt()+1);
        approvalMainService.update(approvalMain);


        return approvalStepInchargeMapper.insert(approvalStepIncharge)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param approvalStepIncharge
     * @return boolean
     * @apiNote: 用一个新的对象更新ApprovalStepIncharge表的一个记录
     */
    @Override
    public boolean update(ApprovalStepIncharge approvalStepIncharge) {
        return approvalStepInchargeMapper.updateByPrimaryKey(approvalStepIncharge) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除ApprovalStepIncharge表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return approvalStepInchargeMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}

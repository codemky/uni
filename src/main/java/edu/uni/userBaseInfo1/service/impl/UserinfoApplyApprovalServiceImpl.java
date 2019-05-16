package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApprovalExample;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyApprovalMapper;
import edu.uni.userBaseInfo1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserinfoApplyApprovalServiceImpl
 * @Description
 * @Date 2019/4/30 15:13
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class UserinfoApplyApprovalServiceImpl implements UserinfoApplyApprovalService {
    //持久层接口的对象
    @Autowired
    private UserinfoApplyApprovalMapper userinfoApplyApprovalMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;


    /**
     * Author: mokuanyuan 16:54 2019/5/11
     * @param userinfoApplyApproval 申请审批的实体类
     * @param roles 该用户所有扮演的角色
     * @return List<UserinfoApplyApproval>
     * @apiNote: 根据审批结果、审批类型、角色名搜素审批表
     */
    @Override
    public List<UserinfoApplyApproval> selectAllByApprovalAndRole(
            UserinfoApplyApproval userinfoApplyApproval, List<String> roles) {

        UserinfoApplyApprovalExample example = new UserinfoApplyApprovalExample();
        UserinfoApplyApprovalExample.Criteria criteria = example.createCriteria();

        //判断审批结果
        if(userinfoApplyApproval.getResult() != null)
            criteria.andResultEqualTo(userinfoApplyApproval.getResult());
        else
            criteria.andResultIsNull();
        //获取类型
        if(userinfoApplyApproval.getInfoType() != null)
            criteria.andInfoTypeEqualTo(userinfoApplyApproval.getInfoType());

        if(roles != null)
            criteria.andRoleNameIn(roles);

        List<UserinfoApplyApproval> userinfoApplyApprovals =
                userinfoApplyApprovalMapper.selectByExample(example);

        return userinfoApplyApprovals;

    }


    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<UserinfoApplyApproval>
     * @apiNote: 查询所有用户信息审批流程记录，不分页
     */
    @Override
    public List<UserinfoApplyApproval> selectAllUserinfoApplyApprovals() {
        return userinfoApplyApprovalMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return UserinfoApplyApproval
     * @apiNote: 通过id查询一个用户信息审批流程记录
     */
    @Override
    public UserinfoApplyApproval selectUserinfoApplyApprovalById(long id) {
        return userinfoApplyApprovalMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<UserinfoApplyApproval>
     * @apiNote: 分页查询所有用户信息审批流程记录
     */
    @Override
    public PageInfo<UserinfoApplyApproval> selectUserinfoApplyApprovalByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<UserinfoApplyApproval> UserinfoApplyApprovals = userinfoApplyApprovalMapper.selectByExample(null);
        //检验查询的结果
        if(UserinfoApplyApprovals !=null){
            return new PageInfo<>(UserinfoApplyApprovals);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApplyApproval
     * @return boolean
     * @apiNote: 插入一条用户信息审批流程记录
     */
    @Override
    public boolean insertUserinfoApplyApproval(UserinfoApplyApproval UserinfoApplyApproval) {
        return  userinfoApplyApprovalMapper.insertSelective(UserinfoApplyApproval) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApplyApproval
     * @return boolean
     * @apiNote: 更新一条用户信息审批流程记录
     */
    @Override
    public boolean updateUserinfoApplyApproval(UserinfoApplyApproval UserinfoApplyApproval) {
        return userinfoApplyApprovalMapper.updateByPrimaryKeySelective(UserinfoApplyApproval) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条用户信息审批流程记录
     */
    @Override
    public boolean deleteUserinfoApplyApproval(long id) {
        return userinfoApplyApprovalMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}

package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.service.AuthService;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.UserinfoApply;
import edu.uni.userBaseInfo1.bean.UserinfoApplyExample;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyMapper;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import edu.uni.userBaseInfo1.service.UserinfoApplyService;
import edu.uni.userBaseInfo1.utils.StaticInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserinfoApplyServiceImpl
 * @Description
 * @Date 2019/4/30 15:13
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class UserinfoApplyServiceImpl implements UserinfoApplyService {
    //持久层接口的对象
    @Autowired
    private UserinfoApplyMapper userinfoApplyMapper;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private AuthService authService;

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;


    /**
     * Author: mokuanyuan 21:21 2019/6/10
     * @param userInfo_apply
     * @param flag 说明是修改还是增加
     * @apiNote: 创建申请记录（由于发出申请时产生）
     */
    @Override
    public boolean createForApply(UserinfoApply userInfo_apply , Integer flag ) {
        edu.uni.auth.bean.User user = authService.getUser();
        if(user == null)
            return false;

        //根据flag（说明是修改还是增加还是批量增加）、登录用户的用户类型、申请的信息类型获取审批业务的名称
        String approvalName = StaticInformation.getApprovalString(user.getUserType(),
                userInfo_apply.getInfoType(),flag);

        //向userinfoApply增加审批业务id
        userInfo_apply.setApprovalMainId(approvalMainService.
                selectIdByName(userInfo_apply.getUniversityId(), approvalName));
        //设置用户信息申请开始时间
        userInfo_apply.setStartTime(new Date());
        //设置用户信息创建时间
        userInfo_apply.setDatetime(new Date());
        //设置用户信息申请为有效
        userInfo_apply.setDeleted(false);
        //插入新的userinfoApply记录
        return insertUserinfoApply(userInfo_apply);

    }

    /**
     * Author: mokuanyuan 10:17 2019/5/17
     * @param userinfoApply
     * @param userId
     * @return List<UserinfoApply>
     * @apiNote: 根据信息类型，申请结果和用户id查询该用户的所有申请信息
     */
    @Override
    public List<UserinfoApply> selectByTypeAndResultAndUserId( UserinfoApply userinfoApply , Long userId ) {
        UserinfoApplyExample example = new UserinfoApplyExample();
        UserinfoApplyExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("start_time ASC");
        if(userinfoApply.getInfoType() != null)
            criteria.andInfoTypeEqualTo(userinfoApply.getInfoType());

        //筛选审批结果的记录
        if(userinfoApply.getId() == null){
            if(userinfoApply.getApplyResult() != null)
                criteria.andApplyResultEqualTo(userinfoApply.getApplyResult());
            if(userinfoApply.getApplyResult() == null)
                criteria.andApplyResultIsNull();
        }

        criteria.andByWhoEqualTo(userId).andDeletedEqualTo(false);

        return userinfoApplyMapper.selectByExample(example);
    }

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<UserinfoApply>
     * @apiNote: 查询所有用户信息申请记录，不分页
     */
    @Override
    public List<UserinfoApply> selectAllUserinfoApplys() {
        return userinfoApplyMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return UserinfoApply
     * @apiNote: 通过id查询一个用户信息申请记录
     */
    @Override
    public UserinfoApply selectUserinfoApplyById(long id) {
        return userinfoApplyMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<UserinfoApply>
     * @apiNote: 分页查询所有用户信息申请记录
     */
    @Override
    public PageInfo<UserinfoApply> selectUserinfoApplyByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<UserinfoApply> UserinfoApplys = userinfoApplyMapper.selectByExample(null);
        //检验查询的结果
        if(UserinfoApplys !=null){
            return new PageInfo<>(UserinfoApplys);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote: 插入一条用户信息申请记录
     */
    @Override
    public boolean insertUserinfoApply(UserinfoApply UserinfoApply) {
        return  userinfoApplyMapper.insertSelective(UserinfoApply) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote: 更新一条用户信息申请记录
     */
    @Override
    public boolean updateUserinfoApply(UserinfoApply UserinfoApply) {
        return userinfoApplyMapper.updateByPrimaryKeySelective(UserinfoApply) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条用户信息申请记录
     */
    @Override
    public boolean deleteUserinfoApply(long id) {
        return userinfoApplyMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}

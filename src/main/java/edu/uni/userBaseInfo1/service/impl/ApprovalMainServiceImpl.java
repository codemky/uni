package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.ApprovalMain;
import edu.uni.userBaseInfo1.bean.ApprovalMainExample;
import edu.uni.userBaseInfo1.bean.ApprovalStepIncharge;
import edu.uni.userBaseInfo1.mapper.ApprovalMainMapper;
import edu.uni.userBaseInfo1.mapper.ApprovalStepInchargeMapper;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import edu.uni.userBaseInfo1.service.ApprovalStepInchargeService;
import org.apache.poi.ss.formula.functions.T;
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
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 18:50 2019/5/8
     * @param schoolId
     * @return List<ApprovalMain>
     * @apiNote: 根据学校id查询所有的审批业务规定
     */
    @Override
    public List<ApprovalMain> selectBySchoolId(Long schoolId) {
        ApprovalMainExample example = new ApprovalMainExample();
        ApprovalMainExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(schoolId);
        criteria.andDeletedEqualTo(false);
        List<ApprovalMain> approvalMains = approvalMainMapper.selectByExample(example);
        return approvalMains;

    }

    /**
     * Author: mokuanyuan 18:52 2019/5/8
     * @param schoolId
     * @param name
     * @param type
     * @return List<ApprovalMain>
     * @apiNote: 根据学校id、业务名称，业务类型查询所有的审批业务规定（其中业务名称是模糊搜索）
     */
    @Override
    public List<ApprovalMain> selectBySchoolIdAndNameAndType(Long schoolId, String name, String type) {
        ApprovalMainExample example = new ApprovalMainExample();
        ApprovalMainExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        if(schoolId != null)
            criteria.andUniversityIdEqualTo(schoolId);
        if(type != null)
            criteria.andTypeEqualTo(type);
        if(name != null)
            criteria.andNameLike("%" + name + "%");

        return approvalMainMapper.selectByExample(example);

    }

    /**
     * Author: mokuanyuan 20:27 2019/5/7
     * @param schoolId
     * @param name
     * @return id
     * @apiNote: 根据审批业务名称查询审批业务记录id
     */
    @Override
    public long selectIdByName(Long schoolId,String name) {
//        创建查询SQL条件语句
        ApprovalMainExample example = new ApprovalMainExample();
        ApprovalMainExample.Criteria criteria = example.createCriteria();
//        查询是某个学校的业务
        criteria.andUniversityIdEqualTo(schoolId);
//        查询某个业务名称的记录
        criteria.andNameEqualTo(name);
//        查询的记录是有效的
        criteria.andDeletedEqualTo(false);
//        根据创建的条件语句查询
        List<ApprovalMain> approvalMains = approvalMainMapper.selectByExample(example);

        if(approvalMains.size() == 1 )
            return approvalMains.get(0).getId();    //正常情况下应该只找到一个有效记录
        else{
            if(approvalMains.size() > 1 ){
                return -2;      //如果查到不止一个有效记录的话，返回-2
            }
            else{
                return -1;      //如果查不到一条记录，则返回-1
            }
        }
    }

    /**
     * Author: mokuanyuan 21:34 2019/5/7
     * @param id
     * @return 总步数
     * @apiNote: 通过审批规定表记录id获取该审批申请的总步数（step_cnt字段值）
     */
    @Override
    public Integer selectStepCntById(Long id) {
        //        创建查询SQL条件语句
        ApprovalMainExample example = new ApprovalMainExample();
        ApprovalMainExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDeletedEqualTo(false);
        List<ApprovalMain> approvalMains =
                approvalMainMapper.selectByExample(example);
        if(approvalMains.size() == 1)
            return approvalMains.get(0).getStepCnt();
        else
            return -1;

    }

    /**
     * Author: mokuanyuan 15:10 2019/5/8
     * @param schoolId
     * @param name
     * @return boolean
     * @apiNote: 检验某个学校是否已经存在同一个审批业务（同一个学校并且同一个业务名）
     */
    @Override
    public boolean isAlreadyExist(Long schoolId, String name) {
        //        创建查询SQL条件语句
        ApprovalMainExample example = new ApprovalMainExample();
        ApprovalMainExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(schoolId);
        criteria.andNameEqualTo(name);
        criteria.andDeletedEqualTo(false);
        Integer count = approvalMainMapper.countByExample(example);
        if( count > 1 )
            return false;
        else
            return true;
    }

    /**
     * Author: mokuanyuan 16:36 2019/5/8
     * @param id
     * @return boolean
     * @apiNote: 把某个审批业务规定逻辑删除，并且把与之相关的每一步规定的记录（approval_step_incharge表）也逻辑删除
     */
    @Override
    public boolean updateToInvalidById(Long id) {
        List<ApprovalStepIncharge> approvalStepIncharges =
                    approvalStepInchargeService.selectByMainId(id);
        approvalStepIncharges.forEach( item -> {
            approvalStepInchargeService.updateToInvalidById(item.getId()); } );
        ApprovalMain approvalMain = approvalMainMapper.selectByPrimaryKey(id);
        approvalMain.setDeleted(true);
        return approvalMainMapper.updateByPrimaryKeySelective(approvalMain) > 0 ? true : false;

    }

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

        if ( !isAlreadyExist(approvalMain.getUniversityId(),approvalMain.getName()) ){
            approvalMain.setStepCnt(0);
            return approvalMainMapper.insert(approvalMain)>0 ? true : false;
        }
        else
            return false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param approvalMain
     * @return boolean
     * @apiNote: 用一个新的对象更新approvalMain表的一个记录
     */
    @Override
    public boolean update(ApprovalMain approvalMain) {
        //计算更新的步骤数和原本的步骤数差
        int diff_step = selectStepCntById(approvalMain.getId()) - approvalMain.getStepCnt();
        //如果更新的步骤数比原本的步骤数少，则把多余的步骤详情记录置为无效记录
        if( diff_step > 1 ){
            List<ApprovalStepIncharge> approvalStepIncharges =
                    approvalStepInchargeService.selectByMainId(approvalMain.getId());
            for(long index=approvalStepIncharges.size(); diff_step > 0 ; index-- ){
                approvalStepInchargeService.updateToInvalidById(index);
            }
            return approvalMainMapper.updateByPrimaryKey(approvalMain) > 0 ? true : false;
        }
        //如果更新的步骤数比原本的步骤数多，则拒绝修改
        if(diff_step < 0)
            return false;

        //如果更新的步骤数比原本的步骤数一样，则照常操作
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

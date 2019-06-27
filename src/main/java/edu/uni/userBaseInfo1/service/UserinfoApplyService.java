package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.User;
import edu.uni.userBaseInfo1.bean.Student;
import edu.uni.userBaseInfo1.bean.UserinfoApply;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface UserinfoApplyService  {



    /**
     * Author: mokuanyuan 18:33 2019/6/11
     * @param map
     * @param type
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 在任何申请页面点击确认申请时
     */
    public boolean clickApply(HashMap<String,Object> map, Integer type, User loginUser, edu.uni.userBaseInfo1.bean.User modifiedUser) throws ParseException;

    /**
     * Author: mokuanyuan 21:21 2019/6/10
     * @param userInfo_apply
     * @apiNote: 创建申请记录（由于发出申请时产生）
     */
    public boolean createForApply(UserinfoApply userInfo_apply);

    /**
     * Author: mokuanyuan 10:17 2019/5/17
     * @param userinfoApply
     * @param userId
     * @return List<UserinfoApply>
     * @apiNote: 根据信息类型，申请结果和用户id查询该用户的所有申请信息
     */
    List<UserinfoApply> selectByTypeAndResultAndUserId( UserinfoApply userinfoApply , Long userId );

    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<UserinfoApply>
     * @apiNote: 查询所有的用户信息申请
     */
    List<UserinfoApply> selectAllUserinfoApplys();

    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return UserinfoApply
     * @apiNote: 根据id查询用户信息申请
     */
    UserinfoApply selectUserinfoApplyById(long id);

    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<UserinfoApply>
     * @apiNote: 分页查询用户信息申请
     */
    PageInfo<UserinfoApply> selectUserinfoApplyByPage(int pageNum);

    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote: 用户信息申请添加UserinfoApply表的一条记录
     */
    boolean insertUserinfoApply(UserinfoApply UserinfoApply);

    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote:  用户更新一个UserinfoApply表的某个记录（传一个新的对象）
     */
    boolean updateUserinfoApply(UserinfoApply UserinfoApply);

    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除UserinfoApply表的某个记录
     */
    boolean deleteUserinfoApply(long id);
}

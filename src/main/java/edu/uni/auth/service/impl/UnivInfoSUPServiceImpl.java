package edu.uni.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.*;
import edu.uni.auth.exception.UnivInfoSUPException;
import edu.uni.auth.mapper.UnivInfoSUPMapper;
import edu.uni.auth.mapper.UniversityMapperOfAuth;
import edu.uni.auth.mapper.UserMapperOfAuth;
import edu.uni.auth.mapper.UserRoleMapper;
import edu.uni.auth.service.UnivInfoSUPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author 何亮
 */
@Service
public class UnivInfoSUPServiceImpl implements UnivInfoSUPService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapperOfAuth userMapper;
    @Autowired
    private UniversityMapperOfAuth universityMapper;
    @Autowired
    private UnivInfoSUPMapper univInfoSUPMapper;

    /**
     * 新增学校信息管理员
     * 前提：
     *     1. 就任用户不能已是其他学校的信息管理员
     *     2. 学校不能已存在学校信息管理员
     * @param uid
     * @param universityId
     * @return
     */
    @Override
    public boolean insert(long uid, long universityId, int status) throws UnivInfoSUPException {
        // 检查用户是否已就任其他学校的信息管理员
        {
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserIdEqualTo(uid);
            int count = userRoleMapper.countByExample(userRoleExample);
            if(count > 0){
                throw new UnivInfoSUPException("该用户已经是其他学校的信息管理员");
            }
        }
        // 检查学校是否已有就任的学校信息管理员
        {
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUniversityIdEqualTo(universityId);
            int count = userRoleMapper.countByExample(userRoleExample);
            if(count > 0){
                throw new UnivInfoSUPException("该学校已存在信息管理员");
            }
        }
        // 插入新学校信息管理员
        UserRole userRole = new UserRole();
        userRole.setRoleId(Role.UnivInfoSUPId);
        userRole.setUserId(uid);
        userRole.setUniversityId(universityId);
        userRole.setStatus(status);
        return userRoleMapper.insert(userRole) > 0 ? true : false;
    }

    /**
     * 删除学校信息管理员
     * @param universityId
     * @return
     */
    @Override
    public boolean delete(long universityId){
        UserRoleExample example = new UserRoleExample();{
            UserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUniversityIdEqualTo(universityId);
        }
        return userRoleMapper.deleteByExample(example) > 0 ? true : false;
    }

    /**
     * 更新学校信息管理员状态
     * @param universityId
     * @param status
     * @return
     * @throws UnivInfoSUPException
     */
    @Override
    public boolean updateStatusByUniversityId(Long universityId, Integer status) {
        UserRole userRole = new UserRole();
        userRole.setStatus(status);

        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUniversityIdEqualTo(universityId);
        criteria.andRoleIdEqualTo(Role.UnivInfoSUPId);
        return userRoleMapper.updateByExampleSelective(userRole, userRoleExample) > 0 ? true : false;
    }

    @Override
    public List<UnivInfoSUP> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andRoleIdEqualTo(Role.UnivInfoSUPId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);

        List<UnivInfoSUP> sups = new LinkedList();
        userRoles.forEach(userRole -> {
            User user = userMapper.selectByPrimaryKey(userRole.getUserId());
            University university = universityMapper.selectByPrimaryKey(userRole.getUniversityId());

            UnivInfoSUP sup = new UnivInfoSUP();
            sup.setUniversityId(university.getId());
            sup.setUniversityName(university.getName());
            sup.setUniversityEname(university.getEname());
            sup.setUniversityTelephone(university.getTelephone());
            sup.setUserName(user.getUserName());
            sup.setUserIdentification(user.getIdentification());
            sup.setUserSex(user.getUserSex());
            sup.setStatus(userRole.getStatus());

            sups.add(sup);
        });
        return sups;
    }

    /**
     * 根据学校的中英文名，分頁模糊搜索符合条件的前十条学校信息管理者信息
     * @param pageNum
     * @param pageSize
     * @param name
     * @param ename
     * @return
     */
    @Override
    public PageInfo selectLikeUnivNameEname(int pageNum, int pageSize, String name, String ename) {
        PageHelper.startPage(pageNum, pageSize);
        List<UnivInfoSUP> sups = univInfoSUPMapper.selectAllByUnivNameAndEname("%" + name + "%", "%" + ename + "%");
        return new PageInfo<>(sups);
    }

    /**
     * 根据信息管理员的用户id，搜索其管理的学校的ID
     * @param supId
     * @return
     */
    @Override
    public Long selectUniversityIdBySupId(long supId) throws UnivInfoSUPException {
        UserRoleExample userRoleExample = new UserRoleExample();{
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserIdEqualTo(supId);
        }
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if(userRoles.size() > 1){
            throw new UnivInfoSUPException("多个学校信息管理员：user id = " + supId);
        }
        return userRoles.size() == 0 ? null : userRoles.get(0).getUniversityId();
    }

    private String likeStr(String key){
        return "%" + key + "%";
    }
}

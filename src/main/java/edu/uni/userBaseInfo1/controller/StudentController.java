package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Field;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.PageBean.ClassmateBean;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author laizhouhao
 * @Description 关于学生主要信息模块的Controller层（Http URL请求）的具体实现方法
 * @Date 10:21 2019/4/30
 **/

//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "学生主要信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/student")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@RestController
public class StudentController {

        @Autowired  //把Student的Service层接口所有的方法自动装配到该对象中
        private StudentService studentService;
        @Autowired
        private UserService userService;
        @Autowired
        private EcommService ecommService;
        @Autowired
        private OtherClassService otherClassService;
        @Autowired
        private PoliticalAffiliationService politicalAffiliationService;
        @Autowired
        ApprovalMainService approvalMainService;
        @Autowired
        UserinfoApplyService userinfoApplyService;
        @Autowired
        UserinfoApplyApprovalService userinfoApplyApprovalService;
        @Autowired
        private RoleService roleService;
        @Autowired
        ApprovalStepInchargeService approvalStepInchargeService;
        @Autowired
        private OtherClassmateService otherClassmateService;
        @Autowired
        private OtherClassmatePositionService otherClassmatePositionService;
        @Autowired
        private OtherSpecialtyService otherSpecialtyService;
        @Autowired
        private SpecialtyService specialtyService;
        @Autowired
        private PositionService positionService;
        @Autowired
        private OtherFieldService otherFieldService;


        @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
        private RedisCache cache;

        //内部类，专门用来管理每个get方法所对应缓存的名称。
        static class CacheNameHelper{
            // ub1_s_student_{学生主要信息记录id}
            public static final String Receive_CacheNamePrefix = "ub1_s_student_";
            // ub1_s_student_listAll
            public static final String ListAll_CacheName = "ub1_s_student_listAll";
        }

    /**
     * Author: laizhouhao 18:36 2019/5/6
     * @param user_id
     * @return response
     * @apiNote: 根据用户的id查询对应的学生主要信息
     */
    @ApiOperation( value = "根据用户的id查询对应的学生主要信息",notes = "2019年5月6日 18:37:01 已通过测试" )
    @GetMapping("studentByUserId/{user_id}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long user_id,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = StudentController.CacheNameHelper.ListAll_CacheName+user_id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("students",studentService.selectByUserId(user_id)).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

        /**
         * Author: laizhouhao 10:29 2019/4/30
         * return response
         * @apiNote: 查询所有学生主要信息记录
         */
        @ApiOperation( value = "查询所有学生主要信息记录",notes = "2019-5-5 15:53:53已通过测试" )
        @GetMapping("students/listAll")
        @ResponseBody
        public void selectAll(HttpServletResponse response) throws IOException {
            response.setContentType("application/json;charset=utf-8");
            String cacheName = edu.uni.userBaseInfo1.controller.StudentController.CacheNameHelper.ListAll_CacheName;
            String json = cache.get(cacheName);
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("students",studentService.selectAll()).convertIntoJSON();
                cache.set(cacheName,json);
            }
            response.getWriter().write(json);
        }

        /**
         * Author: laizhouhao 10:30 2019/4/30
         * @param student
         * @return 新增学生主要信息结果
         * @apiNote 新增学生主要信息
         */
        @ApiOperation(value="新增学生主要信息", notes="2019-5-5 15:53:53已通过测试")
        @ApiImplicitParam(name = "student", value = "学生主要信息详情实体", required = true, dataType = "Student")
        @PostMapping("/student")  //post请求方式
        @ResponseBody
        public Result create(@RequestBody(required = false) Student student){
            //检验页面传来的对象是否存在
            if(student != null){
                boolean success = studentService.insert(student);
                if(success){
                    // 清空相关缓存
                    cache.delete(edu.uni.userBaseInfo1.controller.StudentController.CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
            }
            return Result.build(ResultType.ParamError);
        }

        /**
         * Author: laizhouhao 10:33 2019/4/30
         * @param id
         * @return 删除操作结果
         * @apiNote 根据id删除学生主要信息
         */
        @ApiOperation(value="根据id删除学生主要信息", notes="2019-5-5 15:53:53已通过测试")
        @ApiImplicitParam(name = "id", value = "学生id", required = true, dataType = "Long", paramType = "path")
        @DeleteMapping("/student/{id}")   //delete请求
        @ResponseBody
        public Result destroy(@PathVariable Long id){
            boolean success = studentService.delete(id);
            if(success){
                // 清空相关缓存
                cache.delete(edu.uni.userBaseInfo1.controller.StudentController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }

        /**
         * Author: laizhouhao 10:34 2019/4/30
         * @param student
         * @return 更新操作结果
         * @apiNote 更新学生主要信息
         */
        @ApiOperation(value="更新学生主要信息", notes="2019-5-5 15:53:53已通过测试")
        @ApiImplicitParam(name = "student", value = "学生主要信息实体", required = true, dataType = "Student")
        @PutMapping("/student")   //Put请求
        @ResponseBody
        public Result update(@RequestBody(required = false) Student student){
            if(student != null && student.getId() != null){
                boolean success = studentService.update(student);
                if(success){
                    //清除相应的缓存
                    cache.delete(edu.uni.userBaseInfo1.controller.StudentController.CacheNameHelper.Receive_CacheNamePrefix + student.getId());
                    cache.delete(edu.uni.userBaseInfo1.controller.StudentController.CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
            }
            return Result.build(ResultType.ParamError);
        }

    /**
     * Author: laizhouhao 16:08 2019/5/7
     * @param user_id
     * @return response
     * @apiNote: 根据学生的用户id查找学生个人详细信息
     */
    @ApiOperation( value = "根据学生的用户id查找学生个人详细信息",notes = "未测试" )
    @GetMapping("info/studentDetailInfo/All/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveStudentDetailInfo(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //检验页面传来的id是否存在
        if(user_id != null){
            UserInfo userInfo = new UserInfo();
            //获取学生在用户表的主要信息
            List<User> userList = new ArrayList<>();
            userList.add(userService.selectUserById(user_id));
            //获取学生的照片、地址信息
            userInfo = userService.selectPictureAddrByUserId(user_id);
            userInfo.setUsers(userList);
            //获取学生的学生主要信息
            userInfo.setEcomms(ecommService.selectValidEcomByUserId(user_id));
            //获取学生在学生表的主要信息
            List<Student> studentList = studentService.selectValidStudentByUserId(user_id);
            userInfo.setStudents(studentList);
            //获取政治面貌
            List<PoliticalAffiliation> politicalAffiliationList = new ArrayList<>();
            if(studentList.size()>=1) {
                politicalAffiliationList.add(politicalAffiliationService
                        .selectPoliticalAffiliationById(studentList.get(0).getPoliticalId()));
            }
            userInfo.setPoliticalAffiliations(politicalAffiliationList);
            System.out.println(userInfo);
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = StudentController.CacheNameHelper.Receive_CacheNamePrefix +"studentDetailInfo"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userInfo",userInfo).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }
    @ApiOperation( value = "根据学生的用户id查找学生对应的学院",notes = "未测试" )
    @GetMapping("info/studentDetailInfo/department/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void selectDepartmentIdByStudentId(@PathVariable Long user_id,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        if(user_id != null){
            Student student = studentService.selectByUserId(user_id).get(0);
            Class aClass = otherClassService.selectClassByClassId(student.getClassId());
            System.out.println(aClass.getDepartmentId()+"--->");
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = StudentController.CacheNameHelper.Receive_CacheNamePrefix +"department"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("class",aClass).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }

    }


    @ApiOperation(value="根据年级和专业id查询相应的班级", notes="未测试")
    @ApiImplicitParam(name = "map"  )
    @PostMapping("/ajaxGetClassByGradeAndSpecialtyId")
    @ResponseBody
    public void GetClassByGradeAndSpecialtyId(@RequestBody Map<String ,Object>  map , HttpServletResponse response) throws IOException{

        Integer grade = (Integer) map.get("grade");
        Integer specialtyId = (Integer) map.get("specialtyId");

        response.setContentType("application/json;charset=utf-8");
        List<Class> classes = otherClassService.selectClassByGradeAndSpecialtyId(grade, (long)specialtyId);

        response.getWriter().write(Result.build(ResultType.Success).appendData("classes",classes).convertIntoJSON());

    }


    @ApiOperation(value="根据学校id和专业名称（模糊）查询相应的专业", notes="未测试")
    @ApiImplicitParam(name = "map"  )
    @PostMapping("/ajaxGetSpecialtiesBySchoolIdAndName")
    @ResponseBody
    public void GetSpecialtiesBySchoolIdAndName(@RequestBody Map<String,Object> map ,
                 HttpServletResponse response) throws  IOException{

        String specialtyName = (String) map.get("specialtyName");
        Long schoolId = (long) (1);

        response.setContentType("application/json;charset=utf-8");
        List<Specialty> specialties = otherSpecialtyService.selectBySchoolIdAndSpecialtyName(schoolId, specialtyName);

        response.getWriter().write(Result.build(ResultType.Success).appendData("specialties",specialties).convertIntoJSON());

    }

    /**
     * Author: mokuanyuan 17:24 2019/6/7
     * @apiNote: 根据user_id查询学生主要信息，该方法用于点击申请时先把部分信息发给前端
     */
    @ApiOperation(value="当学生点击申请时交付给前端的部分信息", notes="未测试")
    @ApiImplicitParam(name = "schoolId", value = "学校ID", required = false, dataType = "Integer" )
    @GetMapping("/getSometimeInfoForApply")
    @ResponseBody
    public void getSometimeInfoForApply(HttpServletResponse response) throws IOException{

        Integer schoolId = 1;
        response.setContentType("application/json;charset=utf-8");
        Result result = Result.build(ResultType.Success);

        List<PoliticalAffiliation> politicalAffiliations = politicalAffiliationService.selectAllPoliticalAffiliations();
        result.appendData("political",politicalAffiliations);

        List<Field> fields = otherFieldService.selectAllDormitoriesBySchoolId((long) schoolId);
        result.appendData("Dormitories",fields);

        response.getWriter().write(result.convertIntoJSON());

    }

    @ApiOperation(value="获取学生这部分的信息", notes="未测试")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "Integer" , paramType = "path")
    @GetMapping("/getStudentInformation/{userId}")
    @ResponseBody
    public void getStudentInformation(@PathVariable Long userId ,
                                        HttpServletResponse response) throws IOException{
        User user = userService.selectUserById(userId);
        response.setContentType("application/json;charset=utf-8");
        boolean isOperate = false;
        HashMap<String , Object> studentMap = new HashMap<>();

        if(user != null)
            if(user.getUserType() == 1) {
                List<Student> students = studentService.selectByUserId(userId);
                if (students.size() > 0) {
//                    HashMap<String , Object> studentMap = new HashMap<>();
                    studentService.selectByUserIdToMap(studentMap, students.get(0));
//                    response.getWriter().write(JsonUtils.obj2String(studentMap));
                    isOperate = true;
                }
            }

        if(isOperate)
            response.getWriter().write(Result.build(ResultType.Success).appendData("student",studentService.selectByUserId(userId).get(0))
                    .appendData("studentInfo",studentMap).convertIntoJSON());
        else
            response.getWriter().write(Result.build(ResultType.ParamError).convertIntoJSON());
    }

    /**
     * Author: chenenru 20:50 2019/5/9
     * @param map
     * @return Result
     * @apiNote: 申请修改学生主要信息, 点击确认申请时
     */
    @ApiOperation(value="申请修改学生主要信息", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam( name = "map"  )
    @PostMapping("/applyModifyStudent")
    @ResponseBody
    public Result ApplyModifyStudent(@RequestBody Map<String,Object> map){
        Student student = (Student) map.get("student");

//        //判断前端传过来的值是否为空
//        if(true){
//            if(requestMessage.getStudent()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
//                boolean success = studentService.clickApplyStudent(requestMessage);
//                return success ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
//            }
//        }
//        else
            return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 20:50 2019/5/9
     * @param map
     * @return Result
     * @apiNote: 审批修改学生主要信息的申请, 点击通过时
     */
    @ApiOperation(value="审批修改学生主要信息的申请, 点击通过时", notes="已测试 2019/6/5 21点43分")
    @ApiImplicitParam( name = "map"  )
    @PutMapping("judgeApply")
    @ResponseBody
    public Result commitApplyModifyStudent(@RequestBody Map<String,Object> map){
        Integer userId = (Integer) map.get("user_id");
        userId = 100;
        Integer approvalId = (Integer) map.get("approval_id");
        Integer flag = (Integer) map.get("flag");
        String judgeReason = (String) map.get("Reason");
        boolean isFlag = false;
        if(flag == 0 || flag == -1)
            isFlag = true;
        System.out.println(userId + "###" + approvalId + "$$" + flag + "@@" + judgeReason);
//        List<UserinfoApplyApproval> userinfoApplyApprovals = userinfoApplyApprovalService.selectByApplyId((long)approvalId);
        UserinfoApplyApproval userinfoApplyApproval = userinfoApplyApprovalService.selectUserinfoApplyApprovalById((long) approvalId);
        if(userId != null && userinfoApplyApproval != null && isFlag && judgeReason != null ){
            userinfoApplyApproval.setReason(judgeReason);
            if(flag.equals(0)){
                //比较当前步骤是否是最后一步
                boolean isLast = userService.isLastStep(userinfoApplyApproval.getStep(),userinfoApplyApproval.getUserinfoApplyId());
                //该步骤是最后一步
                if(isLast){
                    //更新
                    boolean firstSuccess = userService.endForPass(userinfoApplyApproval, (long)userId);
                    //判断两个更新是否都成功
                    return firstSuccess ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
                }else{
                    //该审批不是最后一步
                    boolean secondSuccess = userService.createForPass(userinfoApplyApproval, (long)userId);
                    //操作结果
                    return secondSuccess ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
                }
            }
            else{
                if(flag.equals(-1)){
                    boolean success = userService.endForRefuse(userinfoApplyApproval, (long)userId);
                    return success ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
                }
                else
                    return Result.build(ResultType.ParamError);
            }

        }
        return Result.build(ResultType.ParamError);
    }


//    /**
//     * Author: laizhouhao 20:50 2019/5/9
//     * @param map
//     * @return Result
//     * @apiNote: 审批修改学生主要信息的申请, 点击不通过时
//     */
//    @ApiOperation(value="审批修改学生主要信息的申请, 点击不通过时", notes="未测试")
//    @ApiImplicitParam( name = "map"  )
//    @PostMapping(value = "refuseUserinfoApply")
//    @ResponseBody
//    public Result refuseApplyModifyStudent(@RequestBody Map<String,Object> map) throws IOException {
////        System.out.println("小莫是头猪！！！---");
//        Integer userId = (Integer) map.get("user_id");
//        Integer approvalId = (Integer) map.get("approval_id");
//        System.out.println(userId + "###" + approvalId);
//        UserinfoApplyApproval userinfoApplyApproval = userinfoApplyApprovalService.selectUserinfoApplyApprovalById(approvalId);
//
//        if(userId != null && userinfoApplyApproval != null){
//            boolean success = userService.endForRefuse(userinfoApplyApproval, (long)userId);
//            if(success) {
//                return Result.build(ResultType.Success);
//            }else{
//                return Result.build(ResultType.Failed);
//            }
//        }
//        return Result.build(ResultType.ParamError);
//    }

    /**
     * Author: laizhouhao 22:06 2019/6/2
     * @param stu_no
     * @return 学生详细信息
     * @apiNote: 根据学号获取学生详细信息
     */
    @ApiOperation(value="根据学号获取学生详细信息", notes="未测试")
    @ApiImplicitParam(name = "stu_no", value = "学号", required = false, dataType = "String" , paramType = "path")
    @GetMapping(value = "getStuInfoDetailByStuNO/{stu_no}")
    @ResponseBody
    public void selectStudentDetailByStuNo(@PathVariable String stu_no,HttpServletResponse response) throws IOException {
        if (stu_no != null) {
            UserInfo userInfo = new UserInfo();
            userInfo = userService.selectStuDetailInfoByStuNo(stu_no);
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            String json = Result.build(ResultType.Success)
                        .appendData("userInfo", userInfo).convertIntoJSON();

            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

    @ApiOperation(value="根据班级的id查询所有的学生信息", notes="未测试")
    @ApiImplicitParam(name = "classCode", value = "classCode", required = false, dataType = "Long" , paramType = "path")
    @GetMapping("student/allClassmates/{classCode}")
    @ResponseBody
    public  void selectClassesByClassId(@PathVariable String classCode, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json = null;
        List<ClassmateBean> classmateBeans = new ArrayList<>();
        Class aClass = otherClassService.selectClassByClassCode(classCode);
        //System.out.println("bji---->"+aClass.getId());
        List<Classmate> classmates = otherClassmateService.selectByClassId(aClass.getId());
        for (Classmate cm:classmates) {
            Student student = studentService.selectValidStudentByStuId(cm.getStudentId());
            //UserInfo userInfo = userService.selectUserInfoAllByUserId(student.getUserId());
            User user = userService.selectUserById(student.getUserId());
            //ClassmatePosition classmatePosition = otherClassmatePositionService.selectclassmatePositionByClassmateIdAndPositionId(cm.getId(), null);
            ClassmatePosition classmatePosition = otherClassmatePositionService.selectclassmatePositionByClassmateId(cm.getId());
            ClassmateBean classmateBean = new ClassmateBean();
            //学生id
            classmateBean.setStudentId(student.getId());
            //学号
            classmateBean.setStudentNo(student.getStuNo());
            //姓名
            classmateBean.setStudentName(user.getUserName());
            //入学日期
            classmateBean.setBeginLearnDate(student.getBeginLearnDate());
            //主修专业
            Specialty select = specialtyService.select(student.getSpecialtyId());
            classmateBean.setSpecialty(select.getName());
            //所在年级
            classmateBean.setGrade(student.getGrade());
            //性别
            if (user.getUserSex().equals(0)){
                classmateBean.setSex("女");
            }else {
                classmateBean.setSex("男");
            }
            //联系方式
            Ecomm ecomm = ecommService.selectById(student.getPhoneEcommId());
            classmateBean.setPhone(ecomm.getContent());
            //政治面貌
            PoliticalAffiliation politicalAffiliation = politicalAffiliationService.selectPoliticalAffiliationById(student.getPoliticalId());
            classmateBean.setPolitical(politicalAffiliation.getPolitical());
            //岗位
            if (classmatePosition!=null){
                List<Position> positions = positionService.selectAll();
                for (Position p:positions) {
                    if (p.getId().equals(classmatePosition.getPositionId())){
                        classmateBean.setPosition(p.getName());
                        break;
                    }
                }
            }
            //subcalss.add(classmatePosition.getId().toString());
            classmateBeans.add(classmateBean);
        }
        System.out.println(classmateBeans);
        json = Result.build(ResultType.Success).appendData("classmateBeans", classmateBeans).convertIntoJSON();
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 19:35 2019/6/10
     * @param user_id
     * @return 学生信息
     * @apiNote: 根据用户id查找用户的详细信息
     */
    @ApiOperation( value = "根据用户id查找学生的详细信息",notes = "2019年6月10日 19:45:11 已通过" )
    @GetMapping("/getStudentInfo/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveUserPictureAddr(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(user_id != null){
            //查找出学生实体
            List<Student>studentList = new ArrayList<>();
            studentList = studentService.selectByUserId(user_id);
            //查找出学生信息
            HashMap<String,Object>map = new HashMap<>();
            studentService.getStudent(map, studentList);

            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = UserController.CacheNameHelper.Receive_CacheNamePrefix +"studentInfo"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("studentInfo",map).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

}
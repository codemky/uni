package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.GetAddrDetail;
import edu.uni.userBaseInfo1.utils.UserInfo;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author chenenru
 * @Description 关于学生亲属表信息模块的Controller层（Http URL请求）的具体实现方法
 * @Date 10:21 2019/4/30
 **/

//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "学生亲属表信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/studentRelation")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class StudentRelationController {

        @Autowired
        private UserService userService;
        @Autowired
        private StudentService studentService;
        @Autowired  //把StudentRelation的Service层接口所有的方法自动装配到该对象中
        private StudentRelationService studentRelationService;
        @Autowired
        private ApprovalMainService approvalMainService;
        @Autowired
        private UserinfoApplyService userinfoApplyService;
        @Autowired
        private UserinfoApplyApprovalService userinfoApplyApprovalService;

        @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
        private RedisCache cache;



        //内部类，专门用来管理每个get方法所对应缓存的名称。
        static class CacheNameHelper{
            // ub1_s_StudentRelation_{学生亲属表信息记录id}
            public static final String Receive_CacheNamePrefix = "ub1_s_studentRelation_";
            // ub1_s_StudentRelation_listAll
            public static final String ListAll_CacheName = "ub1_s_studentRelation_listAll";
        }


        /**
         * Author: chenenru 10:27 2019/4/30
         * @param id
         * @return response
         * @apiNote: 根据id获取学生亲属表信息
         */
        //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
        //@ApiOperation：用于在swagger2页面显示方法的提示信息
        //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
        //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
        //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
        @ApiOperation( value = "根据id获取学生亲属表信息",notes = "2019-5-5 15:53:53已通过测试" )
        @GetMapping("studentRelation/{id}")
        @ApiImplicitParam(name = "id", value = "studentRelation表的一个id", required = false, dataType = "Long" , paramType = "path")
        @ResponseBody
        public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = StudentRelationController.CacheNameHelper.Receive_CacheNamePrefix + id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                StudentRelation studentRelation = studentRelationService.selectById(id);
                //把查询到的结果用Result工具类转换成json格式的字符串
                json = Result.build(ResultType.Success).appendData("studentRelation",studentRelation).convertIntoJSON();
                //如果有查询到数据，就把在数据库查到的数据放到缓存中
                if(studentRelation != null){
                    cache.set(cacheName,json);
                }
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);

        }

        /**
         * Author: chenenru 10:29 2019/4/30
         * @apiNote: 查询所有学生亲属表信息记录
         */
        @ApiOperation( value = "获取所有学生亲属表信息记录的内容",notes = "2019-5-5 15:53:53已通过测试" )
        @GetMapping("studentRelations/listAll")
        @ResponseBody
        public void selectAll(HttpServletResponse response) throws IOException {
            response.setContentType("application/json;charset=utf-8");
            String cacheName = StudentRelationController.CacheNameHelper.ListAll_CacheName;
            String json = cache.get(cacheName);
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("studentRelations",studentRelationService.selectAll()).convertIntoJSON();
                cache.set(cacheName,json);
            }
            response.getWriter().write(json);
        }


    /**
     * Author: chenenru 0:49 2019/5/5
     * @apiNote: 根据用户的id查询对应的学生亲属记录
     */
    @ApiOperation( value = "根据某用户的id查询对应的其亲属的内容",notes = "2019-5-5 15:53:53已通过测试" )
    @GetMapping("studentRelations/student/{userId}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long userId,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = StudentRelationController.CacheNameHelper.ListAll_CacheName+userId;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("studentRelations",studentRelationService.selectByUserId(userId)).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * @apiNote: 根据亲属的id查询相关的所有学生
     * @param studentRelationId
     */
    @ApiOperation(value = "根据亲属的用户id查询相关的所有学生",notes = "未测试")
    @GetMapping("/studentRelation/students/{studentRelationId}")
    @ResponseBody
    public void selectStudentByStudentRelationId(@PathVariable Long studentRelationId,
                                                 HttpServletResponse response) throws  IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = StudentRelationController.CacheNameHelper.ListAll_CacheName+"studentRelationId"+studentRelationId;
        String json = cache.get(cacheName);
        if(json == null){
            //根据亲属的用户id查询出亲属的实体
            StudentRelation studentRelation = studentRelationService.selectUserIdByRelaId(studentRelationId);
            json = Result.build(ResultType.Success)
                    .appendData("students",studentService.selectByUserId(studentRelation.getUserId())).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

        /**
         * Author: chenenru 10:30 2019/4/30
         * 新增学生亲属表信息
         * @param studentRelation
         * @return 新增学生亲属表信息结果
         */
        @ApiOperation(value="新增电子学生亲属方式", notes="2019-5-5 15:53:53已通过测试")
        @ApiImplicitParam(name = "studentRelation", value = "学生亲属表信息详情实体", required = true, dataType = "StudentRelation")
        @PostMapping("/studentRelation")  //post请求方式
        @ResponseBody
        public Result create(@RequestBody(required = false) StudentRelation studentRelation){
            //检验页面传来的对象是否存在
            if(studentRelation != null){
                boolean success = studentRelationService.insert(studentRelation);
                if(success){
                    // 清空相关缓存
                    cache.delete(StudentRelationController.CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
            }
            return Result.build(ResultType.ParamError);
        }

        /**
         * Author: chenenru 10:33 2019/4/30
         * 删除学生亲属表信息
         * @param id
         * @return 删除操作结果
         */
        @ApiOperation(value="删除学生亲属", notes="2019-5-5 15:53:53已通过测试")
        @ApiImplicitParam(name = "id", value = "学生亲属id", required = true, dataType = "Long", paramType = "path")
        @DeleteMapping("/studentRelation/{id}")   //delete请求
        @ResponseBody
        public Result destroy(@PathVariable Long id){
            boolean success = studentRelationService.delete(id);
            if(success){
                // 清空相关缓存
                cache.delete(StudentRelationController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }

        /**
         * Author: chenenru 10:34 2019/4/30
         * 更新学生亲属表信息
         * @param studentRelation
         * @return 更新操作结果
         */
        @ApiOperation(value="更新学生亲属表信息详情", notes="2019-5-5 15:53:53已通过测试")
        @ApiImplicitParam(name = "studentRelation", value = "学生亲属表信息详情实体", required = true, dataType = "StudentRelation")
        @PutMapping("/studentRelation")   //Put请求
        @ResponseBody
        public Result update(@RequestBody(required = false) StudentRelation studentRelation){
            if(studentRelation != null && studentRelation.getId() != null){
                boolean success = studentRelationService.update(studentRelation);
                if(success){
                    //清除相应的缓存
                    cache.delete(StudentRelationController.CacheNameHelper.Receive_CacheNamePrefix + studentRelation.getId());
                    cache.delete(StudentRelationController.CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
            }
            return Result.build(ResultType.ParamError);
        }

    /**
     * Author: laizhouhao 21:40 2019/5/7
     * @param stu_no
     * @return response
     * @apiNote: 根据学号查找该学生的亲属信息
     */
    @ApiOperation( value = "根据学号查找该学生的亲属信息",notes = "2019年5月8日 18:35:43 已通过测试" )
    @GetMapping("info/studentRelationsInfo/All/{stu_no}")
    @ApiImplicitParam(name = "stu_no", value = "学号stu_no", required = false, dataType = "String" , paramType = "path")
    @ResponseBody
    public void receiveRelations(@PathVariable String stu_no, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(stu_no != null){
            UserInfo userInfo = new UserInfo();

            //先根据学号查询用户id
            Long user_id = studentService.selectByStuNo(stu_no);

            //根据用户id查询出该用户所有的亲属在本系统的id,并将查询出来的放入工具类对象userInfo储存
            List<StudentRelation> relations = studentRelationService.selectByUserId(user_id);
            userInfo.setStudentRelations(relations);

            //根据亲属的rela_id查询亲属咋本系统的信息，即在user表中的信息,从而获取亲属信息
            List<User> relas = new ArrayList<>();
            //查询亲属的用户信息
            for(int i=0; i<relations.size(); i++){
                relas.add(userService.selectUserById(relations.get(i).getRelaId()));
            }
            userInfo.setUsers(relas);

            System.out.println(userInfo.getUsers());

            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = StudentRelationController.CacheNameHelper.Receive_CacheNamePrefix +stu_no;
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
//
//    /**
//     * Author: laizhouhao 20:11 2019/5/13
//     * @param requestMessage
//     * @return Result
//     * @apiNote: 申请学生亲属信息, 点击申请时
//     */
//    @ApiOperation(value="申请学生亲属信息, 点击申请时", notes="2019年5月14日 19:08:05 已通过测试")
//    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
//    @PostMapping("applyModifyStudentRelation/")
//    @ResponseBody
//    public Result applyModifyStudentRelation(@RequestBody RequestMessage requestMessage){
//        if(requestMessage.getByWho()!=null && requestMessage.getStudentRelation()!=null && requestMessage.getUserinfoApply()!=null) {
//            boolean success = studentRelationService.clickApplyStudentRelation(requestMessage);
//            if (success) {
//                //清除相应的缓存
//                cache.delete(StudentRelationController.CacheNameHelper.Receive_CacheNamePrefix + "applyModifydStudentRelation");
//                cache.delete(StudentRelationController.CacheNameHelper.ListAll_CacheName);
//                return Result.build(ResultType.Success);
//            } else {
//                return Result.build(ResultType.Failed);
//            }
//        }else{
//            return Result.build(ResultType.ParamError);
//        }
//    }

    /**
     * Author: laizhouhao 16:08 2019/6/10
     * @param user_id
     * @return 用户的各种亲属信息
     * @apiNote: 根据用户id获取用户的所有亲属信息
     */
    @ApiOperation( value = "根据用户id获取用户的所有亲属信息",notes = "2019年6月10日 16:26:41 已通过测试" )
    @GetMapping("/getStuRelation/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveUserPictureAddr(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(user_id != null){
            //获取该用户的所有亲属实体
            List<StudentRelation> studentRelationList = studentRelationService.selectByUserId(user_id);
            //获取用户所有有效的亲属信息
            HashMap<String, Object>map = new HashMap<>();
            studentRelationService.getStuRelationInfo(map, studentRelationList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(Result.build(ResultType.Success)
                    .appendData("Relation",map).convertIntoJSON());
        }
    }


    /**
         * <p>
         *     上传文件方法
         * </p>
         * @param uploadDir 上传文件目录，如 F:\\file\\ , /home/file/
         * @param file
         * @return 文件名
         * @throws Exception
         */
        private String executeUpload(String uploadDir, MultipartFile file) throws Exception{
            //获取文件后缀名
            //String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //上传文件名
            //String filename = CommonUtils.generateUUID() + suffix;
            String filename = LocalDateTime.now() + "-" + file.getOriginalFilename();
            //服务端保存的文件对象
            File serverFile = new File(uploadDir + filename);
            //将上传的文件写入服务器端文件内
            file.transferTo(serverFile);
            return filename;
        }


    }

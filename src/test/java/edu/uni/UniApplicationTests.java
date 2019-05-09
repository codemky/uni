package edu.uni;

import edu.uni.userBaseInfo1.bean.ApprovalStepIncharge;
import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.mapper.EcommMapper;
import edu.uni.userBaseInfo1.mapper.UserMapper;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import edu.uni.userBaseInfo1.service.ApprovalStepInchargeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Date;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UniApplicationTests {

    @Autowired
    ApprovalStepInchargeService approvalStepInchargeService;

    @Autowired
    ApprovalMainService approvalMainService;


    @Test
    public void testImpl(){

        long main_id = 1;
        long step_id = 1;

//        approvalMainService.updateToInvalidById((long)5);

        System.out.println(approvalMainService.selectBySchoolId((long) 1));
        System.out.println(approvalMainService.selectBySchoolIdAndNameAndType((long) 1, "生" ,null));

//        System.out.println(approvalMainService.selectIdByName((long)2, "审批学生申请修改照片"));
//        long id = approvalMainService.selectIdByName((long)2,"审批学生申请修改照片");
//        System.out.println(approvalMainService.selectStepCntById(id));
//        System.out.println(approvalMainService.isAlreadyExist(id , "审批学生申请修改照片"));
//        System.out.println(approvalStepInchargeService.selectRoleIdByMainIdAndStep(id,2));
//        System.out.println(approvalStepInchargeService.selectByMainId(id).toString());


    }



    @Test
    public void contextLoads() throws IOException {

    }
}

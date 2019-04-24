package edu.uni;

import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.mapper.EcommMapper;
import edu.uni.userBaseInfo1.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniApplicationTests {

    @Autowired
    EcommMapper ecommMapper;


    @Test
    public void testEcomm(){

        for(long i = 0 ; i < 10;i++){
            Ecomm ecomm = new Ecomm(null, i, "第" + i + "条数据“”“", 1, new Date(), i, false);
            ecommMapper.insertSelective(ecomm);

        }


        ecommMapper.selectByExample(null).stream().forEach(item -> {
            System.out.println(item.toString());
        });



    }



    @Test
    public void contextLoads() throws IOException {

    }
}

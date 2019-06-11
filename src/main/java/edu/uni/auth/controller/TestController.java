package edu.uni.auth.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/json/auth")
public class TestController {
    @PostMapping("test")
    public String post(){
        return "新增数据接口测试";
    }

    @DeleteMapping("test")
    public String delete(){
        return "删除数据接口测试";
    }

    @PutMapping("test")
    public String put(){
        return "修改数据接口测试";
    }

    @GetMapping("test")
    public String get(){
        return "获取数据接口测试";
    }

    @PostMapping("test2")
    public String post2(){
        return "新增数据接口测试";
    }

    @DeleteMapping("tes2t")
    public String delete2(){
        return "删除数据接口测试";
    }

    @PutMapping("test2")
    public String put2(){
        return "修改数据接口测试";
    }

    @GetMapping("test2")
    public String get2(){
        return "获取数据接口测试";
    }

}

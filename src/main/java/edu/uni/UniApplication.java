package edu.uni;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan({"edu.uni.*.mapper"})
@EnableSwagger2
public class UniApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniApplication.class, args);
    }

}

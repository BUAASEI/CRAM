package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午12:17 2018/01/22
 * @Modified by:
 */
@MapperScan(basePackages = {"cn.edu.buaa.rec.dao"})
@SpringBootApplication(scanBasePackages = "cn.edu.buaa.rec")

//@ComponentScan(basePackages = {"cn.edu.buaa.rec.service"})
public class Main {


    public static void main(String[] args){

        SpringApplication.run(Main.class);
    }

}

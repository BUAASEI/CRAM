package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午12:17 2018/01/22
 * @Modified by:
 */
@MapperScan(basePackages = {"cn.edu.buaa.rec.dao"})
@SpringBootApplication(scanBasePackages = "cn.edu.buaa.rec")

/*
    如果没有这行注解，那么会报错：
    Description:
    Field sysUserService in cn.edu.buaa.rec.controller.DefaultController required a bean of type 'cn.edu.buaa.rec.service.SysUserService' that could not be found.
    Action:
    Consider defining a bean of type 'cn.edu.buaa.rec.service.SysUserService' in your configuration.

    原因：http://blog.csdn.net/a532672728/article/details/77702772
    @Component：在默认情况下只能扫描与控制器在同一个包下以及其子包下的@Component注解，以及能将指定注解的类自动注册为Bean的@Service@Controller和@ Repository
    接口和对应实现类，放在了与控制器所在包的同级目录下，这样的注解无法被识别

    解决方案2种：
    1）加入下面注解ComponentScan，手动指定扫描范围
    2）将接口和对应实现类，放在main启动类的同一目录或其子目录下

*/
// 手动指定controller的路径
@ComponentScan(basePackages = {"cn.edu.buaa.rec.controller", "cn.edu.buaa.rec.service"})
public class RECApplication {

    public static void main(String[] args){

        SpringApplication.run(RECApplication.class);
    }

}

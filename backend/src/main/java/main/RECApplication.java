package main;

import cn.edu.buaa.rec.dao.RoleMapper;
import cn.edu.buaa.rec.dao.SolutionMapper;
import cn.edu.buaa.rec.dao.UsecaseRoleMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.LinkedList;
import java.util.List;

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

//这儿要单独并列指明 controller 的扫描路径
//无论是上级路径，还是直接改成controller都会报错（前者均找不到，后者找不到Service）
@ComponentScan(basePackages = {"cn.edu.buaa.rec.service", "cn.edu.buaa.rec.controller"})
public class RECApplication {

    public static void main(String[] args){

          SpringApplication.run(RECApplication.class);

//        ApplicationContext context = SpringApplication.run(RECApplication.class);
//        RoleMapper roleMapper = (RoleMapper) BeanFactoryUtils.beanOfTypeIncludingAncestors(context, RoleMapper.class);
//        List<String> rId = new LinkedList<>();
//        rId.add("June");
//        rId.add("asaa");
//        System.out.println(roleMapper.selectIdsByName(rId,2L));
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {//跨域解决
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                两个*，不然403，可以连接，却没有权限
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}

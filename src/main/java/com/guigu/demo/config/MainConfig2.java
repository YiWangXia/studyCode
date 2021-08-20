package com.guigu.demo.config;

import com.guigu.demo.condition.LinuxCondition;
import com.guigu.demo.condition.MyImportBeanDefinitionRegistrar;
import com.guigu.demo.condition.MyImportSelector;
import com.guigu.demo.condition.WindowCondition;
import com.guigu.demo.entity.Color;
import com.guigu.demo.entity.ColorFactoryBean;
import com.guigu.demo.entity.Person;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.*;

@Configuration // 告诉spring这是一个配置类
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) // id默认是组件的全类名
public class MainConfig2 {
    // 默认是单实例对象，ioc启动的时候就会加载到容器；多实例的时候再获取的时候才会加载
//    @Scope("prototype")
    @Bean("person3")
    // 懒加载，容器启动的时候不会创建，
    // 在第一次获取对象的时候才会创建对象
    @Lazy
    public Person person(){
        System.out.println("容器添加person对象...");
        return new Person("张三",25);
    }


    /*
        @Conditional 按照一定条件判断，满足条件才会注册bean
        这里是用在方法上； 也可用在类上统一处理
     */
    @Conditional({WindowCondition.class})
    @Bean("bill")
//    @Conditional()
    public Person person01(){
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person person02(){
        return new Person("linux", 48);
    }


    /*
        给容器注册组件：
        1. 包扫描+组件标注注解（Controller Service Repostory Component）
        2. @Bean导入的第三方包里面的组件
        3. @Import 快速给容器中导入一个组件(ImportSelector,ImportBeanDefinitionRegistrar)
        4. Spring 提供的 FactoryBean（工厂bean）
                默认获取的是工厂bean调用getObject创建的对象
                要获取工厂bean本身，需要给Id前面加上一个&
     */

    @Bean("colorFactoryBean")
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}

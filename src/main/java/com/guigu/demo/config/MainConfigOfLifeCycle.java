package com.guigu.demo.config;

import com.guigu.demo.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// bean的生命周期（创建，初始化，销毁）
/*
    容器管理bean生命周期
    可以自定义初始化和销毁方法，
    指定初始化和销毁方法

    单实例：在容器销毁的时候会销毁
    多实例：容器不会管理这个bean，不会销毁

    1. @Bean(initMethod = "init", destroyMethod = "destory")
    2. 通过让bean实现 InitializingBean,DisposableBean
    3. 可以使用250;
 */
@ComponentScan("com.guigu.demo.entity")
@Configuration
public class MainConfigOfLifeCycle {
    @Bean(initMethod = "init", destroyMethod = "destory")
//    @Scope
    public Car car(){
        return new Car();
    }
}

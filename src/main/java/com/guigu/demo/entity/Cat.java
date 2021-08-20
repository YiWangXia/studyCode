package com.guigu.demo.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("cat无参构造器");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("cat...destroy");

    }

    // 在bean的属性值设置之后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat...afterPropertiesSet");

    }
}

package com.guigu.demo.condition;


import com.guigu.demo.entity.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /*
        AnnotationMetadata：当前类的注解注解信息（@Configration）
        BeanDefinitionRegistry：BeanDefinition注册类
                把所有添加到容器中的bean; 调用
                registry.registerBeanDefinition();手动注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        boolean color = registry.containsBeanDefinition("com.guigu.demo.entity.Color");
        boolean bule = registry.containsBeanDefinition("com.guigu.demo.entity.Blue");

        // 指定bean名称,(还可以指定bean的scope，类型)
        RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
        if (color && bule){
            // 注册一个bean，指定bean的名称
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }


    }
}

package com.guigu.demo.entity;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        System.out.println("color的factoryBean...getObject()");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    // 判断这个bean的scope是单实例还是多实例
    @Override
    public boolean isSingleton() {
        return true;
    }
}

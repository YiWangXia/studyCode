package com.guigu.demo.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

// 自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {

    // 返回值就是要导入到容器中组件的全类名
    // AnnotationMetadata:当前标注@Import注解的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("自定义组件："+importingClassMetadata.getAnnotationTypes());
        return new String[]{"com.guigu.demo.entity.Blue", "com.guigu.demo.entity.Color"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}

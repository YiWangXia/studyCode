package com.guigu.demo.config;

import com.guigu.demo.entity.Person;
import com.guigu.demo.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration // 告诉spring这是一个配置类


@ComponentScans( // 用于加载@ComponentScan可以多指定几个
        @ComponentScan(value = "com.guigu.demo",includeFilters = {
                // 排除组件
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)
                //  只包含的组件，按照注解
//                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class),
                // 按照类型
//                @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes = {BookService.class})
                // 自定义过滤规则
                @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {MyTypeFilter.class})
        },useDefaultFilters = false)
)
public class MainConfig {

    // id默认为方法名，类型为返回值类型
    @Bean
    public Person person(){
        return new Person("lisi",20);
    }
}

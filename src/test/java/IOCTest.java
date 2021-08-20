import com.guigu.demo.config.MainConfig;
import com.guigu.demo.config.MainConfig2;
import com.guigu.demo.entity.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {
    AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
//        System.out.println(context.getBean("person3"));

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
//        System.out.println(context.getBean("person3"));

        // 获取bean的类型
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }

        Map<String, Person> persons = context.getBeansOfType(Person.class);
        System.out.println(persons);
    }


    @Test
    public void test03(){
        String[] beanNamesForType = context1.getBeanNamesForType(Person.class);

        ConfigurableEnvironment environment = context1.getEnvironment();
        // 动态获取环境变量的值
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }

    @Test
    public void testImport(){
        printBeans(context1);

        // 工厂bean获取的是getObject创建的对象
        Object colorFactoryBean = context1.getBean("colorFactoryBean");
        Object colorFactoryBean1 = context1.getBean("colorFactoryBean");
        System.out.println("bean类型："+colorFactoryBean.getClass());
        System.out.println(colorFactoryBean == colorFactoryBean1);

        // 获取工厂bean本身
        Object colorFactoryBean2 = context1.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean2);
    }

    public void printBeans(AnnotationConfigApplicationContext context){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }


}

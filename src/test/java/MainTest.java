import com.guigu.demo.config.MainConfig;
import com.guigu.demo.entity.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(context.getBean("person"));*/

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(context.getBean("person"));
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String type : beanNamesForType) {
            System.out.println("type："+type);

        }

        System.out.println(beanDefinitionNames+" /n"+beanNamesForType);
    }
}

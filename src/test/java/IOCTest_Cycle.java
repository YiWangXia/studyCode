import com.guigu.demo.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Cycle {

    @Test
    public void test01(){
        // 创建IOC容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成");

        // 关闭容器的时候调用销毁方法
        context.close();
    }
}

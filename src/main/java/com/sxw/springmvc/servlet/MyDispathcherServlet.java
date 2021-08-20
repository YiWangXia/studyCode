package com.sxw.springmvc.servlet;

import com.sxw.springmvc.BaseController;
import com.sxw.springmvc.annotation.MyController;
import com.sxw.springmvc.annotation.MyRequestMapping;
import com.sxw.springmvc.annotation.MyScope;
import com.sxw.springmvc.utils.ClassScanner;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author sxw
 * @date 2020-08-31 23:04
 */
@WebServlet(urlPatterns = ".ation", initParams = {@WebInitParam(name = "basePackage", value = "com.sxw.springmvc")})
public class MyDispathcherServlet extends HttpServlet {
    // 单例，存放方法的映射地址，和方法类
    Map<String, Method> methods = new HashMap<>();
    // 存放controller实例，只有controller注解的类才能放入
    Map<String, Object> controllers = new HashMap<>();


    public void init(ServletConfig config){
        String basePackage = config.getInitParameter("basePackage");
        Map<String, Class<?>> classMap = ClassScanner.scannerClass(basePackage);
        Set<Map.Entry<String, Class<?>>> entries = classMap.entrySet();

        for (Map.Entry<String, Class<?>> entry : entries) {
            String className = entry.getKey();
            Class<?> clazz = entry.getValue();
            String path = "";
            try {
                //如果这个类标记了Controller注解
                if (clazz.isAnnotationPresent(MyController.class)) {
                    if (clazz.isAnnotationPresent(MyRequestMapping.class)){
                        MyRequestMapping reqAnno = clazz.getAnnotation(MyRequestMapping.class);
                        path = reqAnno.value();
                    }

                    controllers.put(className, clazz.newInstance());
                    Method[] ms = clazz.getMethods();
                    for (Method method : ms) {
                        //如果这个类标记了RequestMapping注解
                        if (method.isAnnotationPresent(MyRequestMapping.class)){
                            String requestMappingPath = method.getAnnotation(MyRequestMapping.class).value();
                            methods.put(requestMappingPath, method);
                        }
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    protected void service(HttpServletRequest req, HttpServletResponse resp){

        try {
            String uri = req.getRequestURI();
            String contextPath = req.getContextPath();
            String requestMappingPath = uri.substring(contextPath.length(), uri.indexOf(".action"));
            Method method = methods.get(requestMappingPath);
            if (method == null) {
                resp.sendError(404);
                return;
            }
            BaseController controller = null;
            Class<?> requestClass = method.getDeclaringClass();
            if (requestClass.isAnnotationPresent(MyScope.class) &&
                    requestClass.getAnnotation(MyScope.class).value().equals("prototype")){
                controller = (BaseController) controllers.get(method.getDeclaringClass().getName());
            }else {
                controller = (BaseController) method.getDeclaringClass().newInstance();

            }

            controller.init(req, resp);
            method.invoke(controller);
        } catch (IOException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}

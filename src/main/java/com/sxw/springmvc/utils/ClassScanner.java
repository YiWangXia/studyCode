package com.sxw.springmvc.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
/*
注解写完了，但我们要让注解生效怎么办？我们用springmvc的时候都要配置包扫描，
目的是为了包下的注解生效，所以我们可以建一个包扫描类ClassScanner。
这个类的目的是由basePackage(如:com.ming.mvc)得到这个包下的所有.class文件的全限定名，
和Class对象用一个Map容器装载。
 */

public class ClassScanner {

    /**
     *
     * @param basePackage 扫描包名
     * @return Map class的Map集合
     */
    public static Map<String, Class<?>> scannerClass(String basePackage){
        Map<String, Class<?>> result = new HashMap<>();
        //把com.sxw.springmvc 换成com/sxw/springmvc再类加载器读取文件

        try {
            String basePath = basePackage.replaceAll("\\.", "/");
            // 得到com/sxw/mvc的绝对地址
            String rootpath = ClassScanner.class.getClassLoader().getResource(basePath).getPath();
            if (rootpath != null) rootpath.substring(rootpath.indexOf(basePath));
            Enumeration<URL> enumeration = ClassScanner.class.getClassLoader().getResources(basePath);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                if (url.getProtocol().equals("file")) { // 如果是个文件
                    File file = new File(url.getPath().substring(1));
                    scannerFile(file, rootpath, result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 扫描文件
    private static void scannerFile(File folder, String rootpath, Map<String, Class<?>> classes) {
        try {
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isDirectory()) {
                    scannerFile(file, rootpath + file.getName() + "/", classes);
                } else {
                    if (file.getName().endsWith(".class")) {
                        String className = rootpath + file.getName().replaceAll("/", ".");
                        className = className.substring(0, className.indexOf(".class")); // 去掉拓展名得到全限定名
                        //Map容器存储全限定名和Class
                        classes.put(className, Class.forName(className));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}

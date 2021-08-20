package com.sxw.springmvc;


import javax.servlet.http.HttpServlet;
import java.util.concurrent.ConcurrentHashMap;

public class ExtDispatcherServlet extends HttpServlet {
    // mvc bean key=beanid ,value=对象
    private ConcurrentHashMap<String, Object> mvcBean = new ConcurrentHashMap();
    // // mvc 请求方法 key=requestUrl,value=对象
    private ConcurrentHashMap<String, Object> mvcBeanUrl = new ConcurrentHashMap();
    // mvc 请求方法 key=requestUrl,value=方法
    private ConcurrentHashMap<String, Object> mvcMethodUrl = new ConcurrentHashMap();
    public static void main(String[] args) {

    }

    /*
        初始化spring容器
     */
    public void init(){
    }

}

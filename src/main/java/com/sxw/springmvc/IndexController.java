package com.sxw.springmvc;

import com.sxw.springmvc.annotation.MyScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sxw
 * @date 2020-09-01 14:58
 */
@Controller
@RequestMapping("/ming")
@MyScope("prototype")
//singleton
public class IndexController extends BaseController {
    private int age = 1;

    @RequestMapping("/index")
    public void index() {
        age++;
        System.out.println(age);
        System.out.println("index方法执行了username: "+request.getParameter("username"));
    }

    @RequestMapping("/delete")
    public void delete(){
        System.out.println("delete方法执行了");
    }

    @RequestMapping("/search")
    public void search(){
        System.out.println("search方法执行了");
    }
    @RequestMapping("/update")
    public void update(){
        System.out.println("update方法执行了");
    }
}

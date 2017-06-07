package org.yunzhong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by yunzhong on 2017/4/20.
 */
@Controller
@ApiIgnore
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "访问了首页哦";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

    @RequestMapping("/authorize")
    public String authorize() {
        return "有权限访问";
    }
}

package com.kzw.sq.sqcommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    //@RequestParam():请求参数
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    /*
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name",name);
        //此时会自动去templates寻找hello模板
        return "index";
    }
     */
}

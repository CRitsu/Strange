package io.critsu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/h")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(){
        System.out.println("Home pages");
        return "home";
    }
}

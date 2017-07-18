package springtest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/","homePage"})
public class HomeController {
    
    @RequestMapping(method=RequestMethod.GET)
    public String toHome() {
        return "home";
    }
}

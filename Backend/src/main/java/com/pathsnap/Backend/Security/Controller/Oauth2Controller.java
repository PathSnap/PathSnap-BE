package com.pathsnap.Backend.Security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Oauth2Controller {

    @GetMapping("/")
    @ResponseBody
    public String myAPI(){
        return "my route";
    }
}

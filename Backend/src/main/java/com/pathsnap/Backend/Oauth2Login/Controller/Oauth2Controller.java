package com.pathsnap.Backend.Oauth2Login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Oauth2Controller {

    @GetMapping("/home")
    @ResponseBody
    public String myAPI(){
        return "my route";
    }
}

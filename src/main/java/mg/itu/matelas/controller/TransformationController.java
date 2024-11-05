package mg.itu.matelas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/transformation")
public class Transformation {
    @PostMapping("/insert")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}

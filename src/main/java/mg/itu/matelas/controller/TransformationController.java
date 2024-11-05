package mg.itu.matelas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mg.itu.matelas.dto.TransformationDTO;


@Controller
@RequestMapping("/transformation")
public class TransformationController {
    @PostMapping
    public String getMethodName(@RequestBody TransformationDTO param) {
        return new String();
    }
    
}

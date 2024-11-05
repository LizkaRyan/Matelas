package mg.itu.matelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.service.MatelasService;


@Controller
@RequestMapping("/matelas")
public class MatelasController {

    @Autowired
    MatelasService matelasService;

    @PostMapping("/insert/bloc")
    public String insertBloc(@ModelAttribute Matelas matelasInserted) {
        matelasService.save(matelasInserted);
        return "inserted";
    }
    
}

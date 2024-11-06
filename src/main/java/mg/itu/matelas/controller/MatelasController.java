package mg.itu.matelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.service.MatelasService;

@RestController
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

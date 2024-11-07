package mg.itu.matelas.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.matelas.other.ViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.service.MatelasService;

import java.util.List;

@RestController
@RequestMapping("/matelas/rest")
public class MatelasRestController {

    @Autowired
    MatelasService matelasService;

    @GetMapping("/usuel")
    @JsonView(ViewEntity.Public.class)
    public List<Matelas> getUsuels(){
        return matelasService.findUsuel();
    }
}

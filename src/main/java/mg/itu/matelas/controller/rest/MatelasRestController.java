package mg.itu.matelas.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.other.POV;
import mg.itu.matelas.service.MvtStockService;
import org.springframework.web.bind.annotation.*;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.service.MatelasService;

import java.util.List;

@RestController
@RequestMapping("/matelas/rest")
public class MatelasRestController {

    final MatelasService matelasService;

    private final MvtStockService mvtStockService;

    public MatelasRestController(MatelasService matelasService, MvtStockService mvtStockService) {
        this.matelasService = matelasService;
        this.mvtStockService = mvtStockService;
    }

    @GetMapping("/usuel")
    @JsonView(POV.Public.class)
    public List<Matelas> getUsuels(){
        return matelasService.findUsuel();
    }

    @GetMapping("/create_rand")
    @JsonView(POV.Public.class)
    public MvtStock createRand(){
        return mvtStockService.createData();
    }
}

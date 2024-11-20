package mg.itu.matelas.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpSession;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.entity.fabrication.Formule;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.other.POV;
import mg.itu.matelas.service.MvtStockService;
import mg.itu.matelas.service.fabrication.FormuleService;
import mg.itu.matelas.service.fabrication.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.service.MatelasService;

import java.util.List;

@RestController
@RequestMapping("/matelas/rest")
public class MatelasRestController {

    private final MatelasService matelasService;

    private final MvtStockService mvtStockService;

    private final HttpSession httpSession;

    private final FormuleService formuleService;

    private final MachineService machineService;

    public MatelasRestController(MatelasService matelasService, MvtStockService mvtStockService, HttpSession httpSession, FormuleService formuleService, MachineService machineService) {
        this.matelasService = matelasService;
        this.mvtStockService = mvtStockService;
        this.httpSession = httpSession;
        this.formuleService = formuleService;
        this.machineService = machineService;
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

    @GetMapping("/update_all")
    @JsonView(POV.Public.class)
    public String updateAll(){
        if(httpSession.getAttribute("formule")==null){
            httpSession.setAttribute("formule",formuleService.findAll());
        }
        mvtStockService.updateMvtStockWithPrixRevientTheorique((List<Formule>)httpSession.getAttribute("formule"));
        return "Done";
    }

    @GetMapping("/list")
    @JsonView(POV.Public.class)
    public List<Machine> findAll(){
        return machineService.findAllWithEcart();
    }
}

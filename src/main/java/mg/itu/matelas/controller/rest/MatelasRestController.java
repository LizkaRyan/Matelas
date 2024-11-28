package mg.itu.matelas.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpSession;
import mg.itu.matelas.dto.RandomDTO;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
    public String createRand(){
        matelasService.createData();
        return "Vita";
    }


    @PostMapping("/generate")
    public String formGenerate(@ModelAttribute RandomDTO randomDTO){
        System.out.println(randomDTO.getLongueurMin()+" - "+ randomDTO.getLongueurMax());
        System.out.println(randomDTO.getLargeurMin()+" - "+ randomDTO.getLargeurMax());
        System.out.println(randomDTO.getEpaisseurMin()+" - "+ randomDTO.getEpaisseurMax());
        System.out.println(randomDTO.getDateMin()+" - "+ randomDTO.getDateMax());
        matelasService.createData(randomDTO);
        return "Vita";
    }

    /*@PostMapping("/import")
    public String importer(MultipartFile file){
        if(file.isEmpty()){
            return "Le fichier est vide";
        }

        List<String[]> data=new ArrayList<String[]>();
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
        ){
            String line;
            while((line= reader.readLine())!=null){
                String[] values=line.split(",");
                data.add(values);
            }
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        matelasService.save(data);
        return "Vita";
    }*/

    @GetMapping("/update_all")
    @JsonView(POV.Public.class)
    public String updateAll(){
        if(httpSession.getAttribute("formule")==null){
            httpSession.setAttribute("formule",formuleService.findAll());
        }
        mvtStockService.updateMvtStockWithPrixRevientTheorique((List<Formule>)httpSession.getAttribute("formule"));
        return "Done";
    }

    @GetMapping("/import_CSV")
    @JsonView(POV.Public.class)
    public String importCSV(){
        matelasService.saveAll();
        return "Done";
    }

    @GetMapping("/list")
    @JsonView(POV.Public.class)
    public List<Machine> findAll(){
        return machineService.findAllWithEcart();
    }
}

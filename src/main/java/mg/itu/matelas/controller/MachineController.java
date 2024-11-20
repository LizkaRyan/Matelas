package mg.itu.matelas.controller;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.other.POV;
import mg.itu.matelas.service.fabrication.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/machine")
public class MachineController {
    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping
    @JsonView(POV.Public.class)
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView("machine/index");
        model.addObject("machines",machineService.findAllWithEcart());
        return model;
    }

}

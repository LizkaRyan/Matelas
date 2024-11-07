package mg.itu.matelas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mg.itu.matelas.dto.MatelasDTO;
import mg.itu.matelas.repository.MatelasRepository;



@Controller
@RequestMapping("/matelas")
public class MatelasController {
    @Autowired
    private MatelasRepository matelasRepository;

    @GetMapping("/form")
    public ModelAndView getForm() {
        return new ModelAndView("matelas/form");
    }
    
    @PostMapping
    public String insert(@ModelAttribute MatelasDTO bloc) throws Exception{
        matelasRepository.save(bloc.createMatelas());
        return "redirect:/transformation/form";
    }
    
}

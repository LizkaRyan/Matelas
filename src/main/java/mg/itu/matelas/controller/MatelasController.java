package mg.itu.matelas.controller;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.service.MatelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mg.itu.matelas.dto.MatelasDTO;
import mg.itu.matelas.repository.MatelasRepository;



@Controller
@RequestMapping("/matelas")
public class MatelasController {
    @Autowired
    private MatelasService matelasService;

    @Autowired
    private MatelasRepository matelasRepository;

    @GetMapping("/form")
    public ModelAndView getForm() {
        return new ModelAndView("matelas/form");
    }
    
    @PostMapping
    public String insert(@ModelAttribute MatelasDTO bloc) throws Exception{
        matelasService.save(bloc);
        return "redirect:/transformation/form";
    }
    @GetMapping("/list/blocs")
    public ModelAndView getBlocs() {
        ModelAndView model=new ModelAndView("matelas/liste");
        model.addObject("blocs",matelasService.findBloc());
        return model;
    }

    @GetMapping("/list/blocs/page")
    public ModelAndView getBlocs(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int size) {
        ModelAndView model=new ModelAndView("matelas/listePage");
        Page<Matelas> pageBloc=matelasRepository.findAll(PageRequest.of(page-1,size));
        model.addObject("blocs",pageBloc.getContent());
        model.addObject("currentPage",page);
        return model;
    }
    @GetMapping("/update/{id}")
    public ModelAndView getBlocs(@PathVariable("id") Long idBloc) {
        ModelAndView model=new ModelAndView("matelas/formUpdate");
        model.addObject("bloc",matelasService.findById(idBloc));
        return model;
    }
    @PostMapping("/update")
    public String getBlocs(@ModelAttribute MatelasDTO matelasDTO)throws Exception {
        Matelas matelas=matelasService.findById(matelasDTO.getIdMatelas());
        matelas.setPrixUnitaire(matelasDTO.getPrixUnitaire());
        matelasService.save(matelas);
        return "redirect:/matelas/list/blocs";
    }
}

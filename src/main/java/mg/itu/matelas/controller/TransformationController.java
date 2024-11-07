package mg.itu.matelas.controller;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import mg.itu.matelas.dto.BeneficeDTO;
import mg.itu.matelas.dto.Prediction;
import mg.itu.matelas.dto.SommeBenefice;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.other.ViewEntity;
import mg.itu.matelas.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.repository.MatelasRepository;


@Controller
@RequestMapping("/transformation")
public class TransformationController {
    @Autowired
    private MatelasRepository matelasRepository;

    @Autowired
    private TransformationService transformationService;

    @GetMapping("/form")
    public ModelAndView getForm() {
        List<Matelas> blocs=matelasRepository.findBlocNonUtilise();
        return new ModelAndView("transformation/form").addObject("blocs",blocs);
    }

    @GetMapping("/resultat/{id}")
    public ModelAndView getReste(@PathVariable("id") Long idTransformation){
        ModelAndView valiny=new ModelAndView("transformation/resultat");
        List<Matelas> usuels=matelasRepository.findFormUsuel();
        Transformation transformation=transformationService.findTransformationWithReste(idTransformation);
        Matelas bloc=matelasRepository.findById(transformation.getReste().getIdMatelas()).orElseThrow(()->new RuntimeException("Bloc non retrouve"));
        valiny.addObject("minPerte", Prediction.getMinPerte(usuels, bloc));
        valiny.addObject("optimiste",Prediction.getOptimiste(usuels, bloc));
        return valiny;
    }

    @GetMapping("/benefice")
    public ModelAndView getBenefice(){
        ModelAndView valiny=new ModelAndView("benefice");
        List<BeneficeDTO> benefices=transformationService.getBenefice();
        valiny.addObject("sommeBenefice", SommeBenefice.sommer(benefices));
        valiny.addObject("benefices",benefices);
        return valiny;
    }
}

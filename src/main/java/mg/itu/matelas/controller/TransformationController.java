package mg.itu.matelas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.repository.MatelasRepository;


@Controller
@RequestMapping("/transformation")
public class TransformationController {
    @Autowired
    private MatelasRepository matelasRepository;

    @GetMapping("/form")
    public ModelAndView getForm() {
        List<Matelas> blocs=matelasRepository.findBloc();
        return new ModelAndView("transformation/form").addObject("blocs",blocs);
    }
}

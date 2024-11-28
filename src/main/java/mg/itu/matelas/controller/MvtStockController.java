package mg.itu.matelas.controller;

import mg.itu.matelas.dto.RandomDTO;
import mg.itu.matelas.service.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mvt_stock")
public class MvtStockController {
    @Autowired
    private MvtStockService mvtStockService;

    @GetMapping
    public ModelAndView getMvtStock(){
        ModelAndView modelAndView=new ModelAndView("mvtStock/liste");
        modelAndView.addObject("mvtStocks",mvtStockService.findAll());
        modelAndView.addObject("etatStocks",mvtStockService.findEtatStock());
        return modelAndView;
    }

    @GetMapping("/form_generate")
    public ModelAndView formGenerate(){
        return new ModelAndView("mvtStock/form_generate");
    }
}

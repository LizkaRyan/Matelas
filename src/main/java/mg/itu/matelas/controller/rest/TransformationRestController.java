package mg.itu.matelas.controller.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.matelas.dto.TransformationDTO;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.other.ViewEntity;
import mg.itu.matelas.service.TransformationService;


@RestController
@RequestMapping("/transformation/rest")
public class TransformationRestController {
    @Autowired
    private TransformationService transformationService;
    
    @PostMapping
    @JsonView(ViewEntity.Full.class)
    public HashMap<String,Object> getMethodName(@RequestBody TransformationDTO transformation)throws Exception {
        HashMap<String,Object> valiny=new HashMap<String,Object>();
        valiny.put("status",200);
        try {
            valiny.put("answer",transformationService.save(transformation));
        }
        catch (Exception ex){
            valiny.put("status",500);
            valiny.put("answer",ex.getMessage());
        }
        return valiny;
    }
    @GetMapping("/bloc/reste/{id}")
    @JsonView(ViewEntity.Full.class)
    public HashMap<String,Object> getReste(@PathVariable("id") Long id) {
        return transformationService.getPrediction(id);
    }
}

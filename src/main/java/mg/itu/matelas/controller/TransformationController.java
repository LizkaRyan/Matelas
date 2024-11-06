package mg.itu.matelas.controller;

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
@RequestMapping("/transformation")
public class TransformationController {
    @Autowired
    private TransformationService transformationService;
    
    @PostMapping
    @JsonView(ViewEntity.Full.class)
    public Transformation getMethodName(@RequestBody TransformationDTO transformation)throws Exception {
        return transformationService.save(transformation);
    }
    @GetMapping("/bloc/reste/{id}")
    @JsonView(ViewEntity.Full.class)
    public HashMap<String,Object> getReste(@PathVariable("id") Long id) {
        return transformationService.getPrediction(id);
    }
}

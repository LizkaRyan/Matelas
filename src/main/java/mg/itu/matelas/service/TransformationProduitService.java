package mg.itu.matelas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.dto.TransformationDTO;
import mg.itu.matelas.dto.TransformationProduitDTO;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.repository.MatelasRepository;
import mg.itu.matelas.repository.TransformationProduitRepository;
import mg.itu.matelas.repository.TransformationRepository;

@Service
public class TransformationProduitService {
    @Autowired
    private TransformationRepository transformationRepository;

    @Autowired
    private MatelasRepository matelasRepository;

    @Autowired
    private TransformationProduitRepository transformationProduitRepository;

    @Transactional
    public Transformation save(TransformationDTO transformationDTO)throws Exception{
        Matelas bloc=matelasRepository.findById(transformationDTO.getIdBloc()).orElseThrow(()->new RuntimeException("Bloc non retrouve"));

        Transformation transformation=new Transformation();
        transformation.setBloc(bloc);
        transformation=transformationRepository.save(transformation);

        List<TransformationProduit> transformationProduits=new ArrayList<TransformationProduit>();
        for (TransformationProduitDTO transformationProduitDTO : transformationDTO.getTransformationProduit()) {
            Matelas produit=matelasRepository.findById(transformationProduitDTO.getIdProduit()).orElseThrow(()->new RuntimeException("Bloc non retrouve"));
            TransformationProduit transformationProduit=new TransformationProduit();
            transformationProduit.setProduit(produit);
            transformationProduit.setNombre(transformationProduitDTO.getNombre());
            transformationProduit.setTransformation(transformation);
            transformationProduitRepository.save(transformationProduit);
            transformationProduits.add(transformationProduit);
        }
        transformation.setProduit(transformationProduits);
        return transformation;
    }
}

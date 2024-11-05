package mg.itu.matelas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.dto.BeneficeDTO;
import mg.itu.matelas.dto.TransformationProduitDTO;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.repository.MatelasRepository;
import mg.itu.matelas.repository.TransformationProduitRepository;

@Service
public class TransformationProduitService {
    @Autowired
    private TransformationProduitRepository transformationProduitRepository;

    @Autowired
    private MatelasRepository matelasRepository;

    @Transactional
    public List<TransformationProduit> save(List<TransformationProduitDTO> transformationProduitDTOs,Transformation transformation){
        List<TransformationProduit> transformationProduits=new ArrayList<TransformationProduit>();
        for (TransformationProduitDTO transformationProduitDTO : transformationProduitDTOs) {
            Matelas produit=matelasRepository.findById(transformationProduitDTO.getIdProduit()).orElseThrow(()->new RuntimeException("Bloc non retrouve"));
            TransformationProduit transformationProduit=new TransformationProduit();
            transformationProduit.setProduit(produit);
            transformationProduit.setNombre(transformationProduitDTO.getNombre());
            transformationProduit.setTransformation(transformation);
            transformationProduit=transformationProduitRepository.save(transformationProduit);
            transformationProduits.add(transformationProduit);
        }
        return transformationProduits;
    }

    public BeneficeDTO getBenefice(Long idTransformation){
        return transformationProduitRepository.getBeneficeTheorique(idTransformation).orElseThrow(()->new RuntimeException("Transformation non trouve"));
    }
}

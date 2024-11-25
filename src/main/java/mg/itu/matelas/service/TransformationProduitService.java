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
import mg.itu.matelas.repository.TransformationRepository;

@Service
public class TransformationProduitService {
    private final TransformationRepository transformationRepository;

    private final MatelasService matelasService;

    public TransformationProduitService(TransformationRepository transformationRepository, MatelasService matelasService) {
        this.transformationRepository = transformationRepository;
        this.matelasService = matelasService;
    }

    @Transactional
    public List<TransformationProduit> save(List<TransformationProduitDTO> transformationProduitDTOs,Transformation transformation)throws Exception{
        List<TransformationProduit> transformationProduits=new ArrayList<TransformationProduit>();
        for (TransformationProduitDTO transformationProduitDTO : transformationProduitDTOs) {
            if(transformationProduitDTO.getNombre()==0){
                continue;
            }
            Matelas produit=matelasService.findById(transformationProduitDTO.getIdProduit());
            TransformationProduit transformationProduit=new TransformationProduit();
            transformationProduit.setProduit(produit);
            transformationProduit.setNombre(transformationProduitDTO.getNombre());
            transformationProduit.setTransformation(transformation);
            transformationProduit.setPrixUnitaire(produit.getPrixUnitaire());
            transformationProduit.setPrixRevient(transformation.getBloc());
            //transformationProduit=transformationProduitRepository.save(transformationProduit);
            transformationProduits.add(transformationProduit);
        }
        return transformationProduits;
    }
}

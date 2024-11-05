package mg.itu.matelas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.dto.TransformationDTO;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.other.ConstanteEtat;
import mg.itu.matelas.repository.MatelasRepository;
import mg.itu.matelas.repository.TransformationRepository;

@Service
public class TransformationService {
    @Autowired
    private TransformationRepository transformationRepository;

    @Autowired
    private MatelasRepository matelasRepository;

    @Autowired
    private TransformationProduitService transformationProduitService;

    @Autowired
    private MvtStockService mvtStockService;

    @Transactional
    public Transformation save(TransformationDTO transformationDTO)throws Exception{
        Matelas bloc=matelasRepository.findById(transformationDTO.getIdBloc()).orElseThrow(()->new RuntimeException("Bloc non retrouve"));
        bloc.setEtat(ConstanteEtat.UTILISE);
        //insertion bloc
        Transformation transformation=new Transformation();
        transformation.setBloc(bloc);
        transformation=transformationRepository.save(transformation);

        //insertion transformation produit
        List<TransformationProduit> transformationProduits=transformationProduitService.save(transformationDTO.getTransformationProduit(), transformation);
        transformation.setProduit(transformationProduits);

        //insertion mouvement stock
        mvtStockService.save(transformation);
        return transformation;
    }
}

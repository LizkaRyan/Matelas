package mg.itu.matelas.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.dto.BeneficeDTO;
import mg.itu.matelas.dto.Prediction;
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

        // Insertion reste
        Matelas reste=new Matelas();
        reste.setReste(transformationDTO,bloc);
        reste=matelasRepository.save(reste);

        // Insertion bloc
        Transformation transformation=new Transformation();
        transformation.setBloc(bloc);
        transformation.setReste(reste);

        // Insertion transformation produit
        List<TransformationProduit> transformationProduits=transformationProduitService.save(transformationDTO.getTransformationProduit(), transformation);
        transformation.setProduit(transformationProduits);

        transformation=transformationRepository.save(transformation);
        // Insertion mouvement stock
        mvtStockService.save(transformation);

        return transformation;
    }

    @Transactional
    public HashMap<String,Object> getPrediction(Long idTransformation){
        HashMap<String,Object> valiny=new HashMap<String,Object>();
        List<Matelas> usuels=matelasRepository.findFormUsuel();
        Transformation transformation=transformationRepository.findById(idTransformation).orElseThrow(()->new RuntimeException("Transformation non trouve"));
        if(transformation.getReste()==null){
            throw new RuntimeException("Ce transformation n'a pas eu de reste");
        }
        Matelas bloc=matelasRepository.findById(transformation.getReste().getIdMatelas()).orElseThrow(()->new RuntimeException("Bloc non retrouve"));
        valiny.put("MinPerte",Prediction.getMinPerte(usuels, bloc));
        valiny.put("Optimiste",Prediction.getOptimiste(usuels, bloc));
        valiny.put("benefice",getBenefice(idTransformation));
        return valiny;
    }

    public BeneficeDTO getBenefice(Long idTransformation){
        return transformationRepository.getBeneficeTheorique(idTransformation);
    }
}

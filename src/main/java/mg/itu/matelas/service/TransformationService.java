package mg.itu.matelas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mg.itu.matelas.config.TransformationConfig;
import mg.itu.matelas.dto.SommeBenefice;
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
    private final TransformationRepository transformationRepository;

    private final MatelasService matelasService;

    private final TransformationProduitService transformationProduitService;

    private final MvtStockService mvtStockService;

    private final TransformationConfig transformationConfig;

    public TransformationService(TransformationRepository transformationRepository, MatelasService matelasService, TransformationProduitService transformationProduitService, MvtStockService mvtStockService, TransformationConfig transformationConfig) {
        this.transformationRepository = transformationRepository;
        this.matelasService = matelasService;
        this.transformationProduitService = transformationProduitService;
        this.mvtStockService = mvtStockService;
        this.transformationConfig = transformationConfig;
    }


    public List<Transformation> findAll(){
        return  transformationRepository.findAll();
    }

    @Transactional
    public List<SommeBenefice> predictBenefice(SommeBenefice sommeBenefice){
        List<SommeBenefice> valiny=new ArrayList<SommeBenefice>();
        List<Transformation> transformations=this.findAll();
        List<Matelas> usuels=matelasService.findUsuel();
        valiny.add(predictBeneficeOptimiste(sommeBenefice,transformations,usuels));
        valiny.add(predictBeneficeMinPerte(sommeBenefice,transformations,usuels));
        return valiny;
    }

    @Transactional
    public SommeBenefice predictBeneficeMinPerte(SommeBenefice sommeBenefice,List<Transformation> transformations,List<Matelas> usuels){
        SommeBenefice valiny=new SommeBenefice();
        //valiny.setBenefice(sommeBenefice.getBeneficeTheorique());
        valiny.setBenefice(0);
        valiny.setRemarque("Minimum de perte");
        //valiny.setPrixVente(sommeBenefice.getPrixVente());
        valiny.setPrixVente(0);
        //valiny.setPrixRevient(sommeBenefice.getPrixRevient());
        valiny.setPrixRevient(0);
        for (int i = 0; i < transformations.size(); i++) {
            Matelas bloc=matelasService.findById(transformations.get(i).getReste().getIdMatelas());
            Prediction prediction=Prediction.getMinPerte(usuels, bloc);
            valiny.setPrixVente(valiny.getPrixVente()+(prediction.getNombreCreer()*prediction.getUsuel().getPrixUnitaire()));
            valiny.setPrixRevient(valiny.getPrixRevient()+(prediction.getNombreCreer()*prediction.getUsuel().getPrixUnitaireByOrigine(bloc)));
        }
        valiny.setBenefice(valiny.getPrixVente()- valiny.getPrixRevient());
        return valiny;
    }

    @Transactional
    public SommeBenefice predictBeneficeOptimiste(SommeBenefice sommeBenefice,List<Transformation> transformations,List<Matelas> usuels){
        SommeBenefice valiny=new SommeBenefice();
//        valiny.setBenefice(sommeBenefice.getBeneficeTheorique());
        valiny.setBenefice(0);
        valiny.setRemarque("Optimiste");
//        valiny.setPrixVente(sommeBenefice.getPrixVente());
        valiny.setPrixVente(0);
        valiny.setPrixRevient(0);
        //valiny.setPrixRevient(sommeBenefice.getPrixRevient());
        for (int i = 0; i < transformations.size(); i++) {
            Matelas bloc=matelasService.findById(transformations.get(i).getReste().getIdMatelas());
            Prediction prediction=Prediction.getOptimiste(usuels, bloc);
            valiny.setPrixVente(valiny.getPrixVente()+(prediction.getNombreCreer()*prediction.getUsuel().getPrixUnitaire()));
            valiny.setPrixRevient(valiny.getPrixRevient()+(prediction.getNombreCreer()*prediction.getUsuel().getPrixUnitaireByOrigine(bloc)));
        }
        valiny.setBenefice(valiny.getPrixVente()- valiny.getPrixRevient());
        return valiny;
    }

    @Transactional
    public Transformation save(TransformationDTO transformationDTO)throws Exception{
        Matelas bloc=matelasService.findById(transformationDTO.getIdBloc());
        bloc.setEtat(ConstanteEtat.UTILISE);

        // Insertion reste
        Matelas reste=new Matelas();
        reste.setReste(transformationDTO,bloc);
        reste=matelasService.save(reste);

        // Insertion bloc
        Transformation transformation=new Transformation();
        transformation.setBloc(bloc);
        transformation.setReste(reste);
        transformation.setRemarque(transformationDTO.getRemarque());
        transformation.setDateTransformation(transformationDTO.getDateTransformation());

        // Insertion transformation produit
        List<TransformationProduit> transformationProduits=transformationProduitService.save(transformationDTO.getTransformationProduits(), transformation);
        transformation.setProduit(transformationProduits);

        this.controller(transformation);
        transformation=transformationRepository.save(transformation);
        // Insertion mouvement stock
        mvtStockService.save(transformation);

        return transformation;
    }

    @Transactional
    public HashMap<String,Object> getPrediction(Long idTransformation){
        HashMap<String,Object> valiny=new HashMap<String,Object>();
        List<Matelas> usuels=matelasService.findUsuel();
        Transformation transformation=transformationRepository.findById(idTransformation).orElseThrow(()->new RuntimeException("Transformation non trouve"));
        if(transformation.getReste()==null){
            throw new RuntimeException("Ce transformation n'a pas eu de reste");
        }
        Matelas bloc=matelasService.findById(transformation.getReste().getIdMatelas());
        valiny.put("MinPerte",Prediction.getMinPerte(usuels, bloc));
        valiny.put("Optimiste",Prediction.getOptimiste(usuels, bloc));
        valiny.put("benefice",getBenefice());
        return valiny;
    }

    public Transformation findTransformationWithReste(Long idTransformation){
        Transformation transformation=transformationRepository.findById(idTransformation).orElseThrow(()->new RuntimeException("Transformation non trouve"));
        if(transformation.getReste()==null){
            throw new RuntimeException("Ce transformation n'a pas eu de reste");
        }
        return transformation;
    }

    public List<BeneficeDTO> getBenefice(){
        return transformationRepository.getBeneficeTheorique();
    }

    public void controller(Transformation transformation){
        double sommeVolume=0;
        for (int i = 0; i < transformation.getProduit().size(); i++) {
            sommeVolume+=transformation.getProduit().get(i).getNombre()*transformation.getProduit().get(i).getProduit().getVolume();
        }
        System.out.println(transformation.getReste().getVolume()+" reste");
        sommeVolume+=transformation.getReste().getVolume();
        double volumePerdu=transformation.getBloc().getVolume()-sommeVolume;
        double volumePercented=transformation.getBloc().getVolume()*transformationConfig.getPercentage()/100.0;
        /*if(transformation.getBloc().getLongueur()<transformation.getReste().getLongueur()){
            throw new RuntimeException("Longueur du reste doit être inférieur au longueur d'origine");
        }
        if(transformation.getBloc().getLargeur()<transformation.getReste().getLargeur()){
            throw new RuntimeException("Largeur du reste doit être inférieur au longueur d'origine");
        }
        if(transformation.getBloc().getEpaisseur()<transformation.getReste().getEpaisseur()){
            throw new RuntimeException("Epaisseur du reste doit être inférieur au longueur d'origine");
        }*/
        if(volumePerdu>volumePercented){
            throw new RuntimeException("Trop de perdu "+transformationConfig.getPercentage()+"% de "+transformation.getBloc().getVolume()+" = "+volumePercented+" alors que le perdu est "+volumePerdu);
        }
        if(volumePerdu<0){
            throw new RuntimeException("La volume du bloc n'est pas suffisante volume perdu = "+volumePerdu+" sommeVolume = "+sommeVolume+" bloc origine "+transformation.getBloc().getVolume());
        }
    }
}

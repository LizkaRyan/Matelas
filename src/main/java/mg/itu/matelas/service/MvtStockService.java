package mg.itu.matelas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.repository.MvtStockRepository;

@Service
public class MvtStockService {
    @Autowired
    private MvtStockRepository mvtStockRepository;

    public MvtStock save(MvtStock mvtStock){
        return mvtStockRepository.save(mvtStock);
    }

    @Transactional
    public List<MvtStock> save(Transformation transformation){
        List<MvtStock> mvtStocks=new ArrayList<MvtStock>();
        //Insertion mouvement stock produit
        for (TransformationProduit produits : transformation.getProduit()) {
            MvtStock mvtStock=new MvtStock(produits);
            mvtStocks.add(mvtStockRepository.save(mvtStock));
        }
        //Insertion mouvement stock bloc
        
        return mvtStocks;
    }
}

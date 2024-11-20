package mg.itu.matelas.service;

import java.util.ArrayList;
import java.util.List;

import mg.itu.matelas.dto.EtatStock;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.fabrication.Formule;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.service.fabrication.MachineService;
import mg.itu.matelas.service.fabrication.MvtStockMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.repository.MvtStockRepository;

@Service
public class MvtStockService {
    private final MvtStockRepository mvtStockRepository;

    private final MachineService machineService;

    private final MvtStockMatiereService mvtStockMatiereService;

    public MvtStockService(MachineService machineService, MvtStockRepository mvtStockRepository, MvtStockMatiereService mvtStockMatiereService) {
        this.machineService = machineService;
        this.mvtStockRepository = mvtStockRepository;
        this.mvtStockMatiereService = mvtStockMatiereService;
    }

    @Transactional
    public MvtStock save(MvtStock mvtStock){
        return mvtStockRepository.save(mvtStock);
    }

    public void flush(){
        mvtStockRepository.flush();
    }

    @Transactional
    public List<MvtStock> findMvtBloc(){
        return mvtStockRepository.findMvtBloc();
    }

    @Transactional
    public MvtStock createData(){
        List<Machine> machines=machineService.findAll();
        List<Matelas> matelasList=Matelas.init();
        MvtStock mvtStock=null;
        for (int i = 0; i < matelasList.size(); i++) {
            matelasList.get(i).setMatelas("Matelas "+(i+1));
            mvtStock=new MvtStock(matelasList.get(i),machines.get(i%machines.size()));
            mvtStock=this.save(mvtStock);
        }
        for (int i = 0; i < 10-matelasList.size(); i++) {
            Matelas matelas=new Matelas(Matelas.getMoyennePRU(matelasList),10);
            matelas.setMatelas("Matelas "+(i+1));
            mvtStock=new MvtStock(matelas,machines.get(i%machines.size()));
            mvtStock=this.save(mvtStock);
        }
        return mvtStock;
    }

    @Transactional
    public void updateMvtStockWithPrixRevientTheorique(List<Formule> formules){
        List<MvtStock> mvtStocks=this.findMvtBloc();
        for (MvtStock mvtStock:mvtStocks) {
            mvtStock.setPrixRevientTheorique(mvtStockMatiereService.findMvtStockMatiereGroupByMatiere(),formules);
            this.save(mvtStock);
        }
    }

    @Transactional
    public List<MvtStock> save(Transformation transformation){
        List<MvtStock> mvtStocks=new ArrayList<MvtStock>();
        //Insertion mouvement stock produit
        for (TransformationProduit produits : transformation.getProduit()) {
            MvtStock mvtStock=new MvtStock(produits,transformation.getBloc());
            mvtStock.setTransformation(transformation);
            mvtStocks.add(mvtStockRepository.save(mvtStock));
        }
        //Insertion mouvement stock bloc
        MvtStock sortieBloc=MvtStock.sortieBloc(transformation.getBloc(),transformation.getDateTransformation());
        mvtStocks.add(mvtStockRepository.save(sortieBloc));
        return mvtStocks;
    }

    @Transactional
    public List<MvtStock> findAll(){
        return mvtStockRepository.findAll();
    }
    @Transactional
    public List<EtatStock> findEtatStock(){return mvtStockRepository.findEtatStock();}
}

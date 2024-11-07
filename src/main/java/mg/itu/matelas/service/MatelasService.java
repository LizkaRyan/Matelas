package mg.itu.matelas.service;

import mg.itu.matelas.dto.MatelasDTO;
import mg.itu.matelas.entity.MvtStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.repository.MatelasRepository;

import java.util.List;

@Service
public class MatelasService {
    @Autowired
    MatelasRepository matelasRepository;

    @Autowired
    MvtStockService mvtStockService;

    @Transactional
    public Matelas save(MatelasDTO matelasInserted)throws Exception{
        Matelas matelas=matelasInserted.createMatelas();
        matelas=matelasRepository.save(matelas);
        MvtStock mvtStock=MvtStock.entreeBloc(matelas,matelasInserted.getDateInsertion());
        mvtStockService.save(mvtStock);
        return matelas;
    }

    public List<Matelas> findUsuel(){
        return matelasRepository.findFormUsuel();
    }
}

package mg.itu.matelas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.repository.MatelasRepository;

@Service
public class MatelasService {
    @Autowired
    MatelasRepository matelasRepository;

    @Transactional
    public Matelas save(Matelas matelasInserted){
        Matelas matelas=matelasRepository.findByDimension(matelasInserted.getLongueur(), matelasInserted.getLargeur(), matelasInserted.getEpaisseur()).orElse(null);
        if(matelas!=null){
            return matelas;
        }
        return matelasRepository.save(matelas);
    }
}

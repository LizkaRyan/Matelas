package mg.itu.matelas.service.fabrication;

import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import mg.itu.matelas.repository.fabrication.MvtStockMatiereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MvtStockMatiereService {
    private final MvtStockMatiereRepo mvtStockMatiereRepo;

    public MvtStockMatiereService(MvtStockMatiereRepo mvtStockMatiereRepo) {
        this.mvtStockMatiereRepo = mvtStockMatiereRepo;
    }

    public List<MvtStockMatiere> findAllOrderByDate(){
        return mvtStockMatiereRepo.findAllOrderByDateAsc();
    }
}

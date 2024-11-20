package mg.itu.matelas.service.fabrication;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.fabrication.MatierePremiere;
import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import mg.itu.matelas.repository.fabrication.MatierePremiereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MatierePremiereService {
    private final MatierePremiereRepo matierePremiereRepo;

    public MatierePremiereService(MatierePremiereRepo matierePremiereRepo) {
        this.matierePremiereRepo = matierePremiereRepo;
    }

    public List<MatierePremiere> findAll(){
        return matierePremiereRepo.findAll();
    }
}

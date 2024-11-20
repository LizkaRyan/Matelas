package mg.itu.matelas.service.fabrication;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.fabrication.MatierePremiere;
import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import mg.itu.matelas.repository.fabrication.MvtStockMatiereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MvtStockMatiereService {
    private final MvtStockMatiereRepo mvtStockMatiereRepo;

    private final MatierePremiereService matierePremiereService;

    public MvtStockMatiereService(MvtStockMatiereRepo mvtStockMatiereRepo, MatierePremiereService matierePremiereService) {
        this.mvtStockMatiereRepo = mvtStockMatiereRepo;
        this.matierePremiereService = matierePremiereService;
    }

    @Transactional
    public HashMap<Long, List<MvtStockMatiere>> findMvtStockMatiereGroupByMatiere(){
        List<MatierePremiere> matierePremieres=matierePremiereService.findAll();
        HashMap<Long,List<MvtStockMatiere>> answer=new HashMap<Long,List<MvtStockMatiere>>();
        for (MatierePremiere matierePremiere:matierePremieres) {
            Long idMatiere=matierePremiere.getIdMatierePremiere();
            answer.put(idMatiere,this.findByIdMatiere(idMatiere));
        }
        return answer;
    }

    @Transactional
    public List<MvtStockMatiere> findByIdMatiere(Long idMatiere){
        return mvtStockMatiereRepo.findByIdMatiereOrderByDateAsc(idMatiere);
    }

    public List<MvtStockMatiere> findAllOrderByDate(){
        return mvtStockMatiereRepo.findAllOrderByDateAsc();
    }
}

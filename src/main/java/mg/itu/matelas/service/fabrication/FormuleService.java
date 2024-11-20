package mg.itu.matelas.service.fabrication;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.fabrication.Formule;
import mg.itu.matelas.repository.fabrication.FormuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormuleService {
    private final FormuleRepo formuleRepo;

    public FormuleService(FormuleRepo formuleRepo) {
        this.formuleRepo = formuleRepo;
    }

    @Transactional
    public List<Formule> findAll(){
        return formuleRepo.findAll();
    }
}

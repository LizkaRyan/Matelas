package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.MatierePremiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatierePremiereRepo extends JpaRepository<MatierePremiere,Long> {
}

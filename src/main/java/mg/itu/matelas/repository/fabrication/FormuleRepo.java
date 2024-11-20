package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.Formule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormuleRepo extends JpaRepository<Formule,Long> {
}

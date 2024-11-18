package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepo extends JpaRepository<Machine,Long> {
}

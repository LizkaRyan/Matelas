package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepo extends JpaRepository<Machine,Long> {
    @Query("select new mg.itu.matelas.entity.fabrication.Machine(ma.idMachine,ma.machine,AVG(mv.ecart)) from MvtStock mv join mv.machine ma group by ma.idMachine,ma.machine order by AVG(mv.ecart) asc")
    public List<Machine> findMachineWithEcart();
}

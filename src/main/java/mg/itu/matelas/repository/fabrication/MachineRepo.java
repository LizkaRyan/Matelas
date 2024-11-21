package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MachineRepo extends JpaRepository<Machine,Long> {
    @Query("select new mg.itu.matelas.entity.fabrication.Machine(ma.idMachine,ma.machine,AVG(mv.ecart),sum(mv.prixRevient),sum(mv.prixRevient-(mv.ecart*mv.matelas.longueur*mv.matelas.largeur*mv.matelas.epaisseur)),sum(mv.matelas.longueur*mv.matelas.largeur*mv.matelas.epaisseur)) from MvtStock mv join mv.machine ma group by ma.idMachine,ma.machine order by AVG(mv.ecart) asc")
    public List<Machine> findMachineWithEcart();

    @Query("select new mg.itu.matelas.entity.fabrication.Machine(ma.idMachine,ma.machine,AVG(mv.ecart),sum(mv.prixRevient),sum(mv.prixRevient-mv.ecart),sum(mv.matelas.longueur*mv.matelas.largeur*mv.matelas.epaisseur)) from MvtStock mv join mv.machine ma where extract(year from mv.dateMvtStock)=:annee group by ma.idMachine,ma.machine order by AVG(mv.ecart) asc")
    public List<Machine> findMachineWithEcartByAnnee(@Param("annee") int annee);

    @Query(value = "select * from temp_table",nativeQuery = true)
    public List<HashMap<String,String>> findTableTemp();
}

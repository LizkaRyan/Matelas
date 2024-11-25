package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.MatierePremiere;
import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MvtStockMatiereRepo extends JpaRepository<MvtStockMatiere,Long> {
    @Query("select m from MvtStockMatiere m order by m.dateMvt asc")
    public List<MvtStockMatiere> findAllOrderByDateAsc();
    @Query("select " +
            "new mg.itu.matelas.entity.fabrication.MvtStockMatiere(" +
            "m.idMvtStockMatiere,m.quantite,m.prixUnitaire,m.dateMvt,m.matierePremiere)" +
            " from MvtStockMatiere m where m.matierePremiere.idMatierePremiere=:idMatiere order by m.dateMvt asc")
    public List<MvtStockMatiere> findByIdMatiereOrderByDateAsc(@Param("idMatiere") Long idMatiere);
}

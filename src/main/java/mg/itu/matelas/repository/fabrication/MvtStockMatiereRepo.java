package mg.itu.matelas.repository.fabrication;

import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MvtStockMatiereRepo extends JpaRepository<MvtStockMatiere,Long> {
    @Query("select m from MvtStockMatiere m order by m.dateMvt asc")
    public List<MvtStockMatiere> findAllOrderByDateAsc();
}

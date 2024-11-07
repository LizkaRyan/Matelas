package mg.itu.matelas.repository;

import mg.itu.matelas.dto.EtatStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mg.itu.matelas.entity.MvtStock;

import java.util.List;

@Repository
public interface MvtStockRepository extends JpaRepository<MvtStock, Long> {
    @Query(value = "select sum(entree)-sum(sortie) as etat, matelas from mvt_stock natural join matelas group by id_matelas,matelas",nativeQuery = true)
    List<EtatStock> findEtatStock();
}

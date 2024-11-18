package mg.itu.matelas.repository;

import mg.itu.matelas.dto.EtatStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mg.itu.matelas.entity.MvtStock;

import java.util.List;

@Repository
public interface MvtStockRepository extends JpaRepository<MvtStock, Long> {
    @Query(value = "select matelas,sum(entree*prix_revient)/sum(entree) as prix_revient,sum(entree) as etat from mvt_stock natural join matelas where id_type_matelas=2 and sortie=0 group by matelas",nativeQuery = true)
    List<EtatStock> findEtatStock();

    @Query("select m from MvtStock m where m.matelas.typeMatelas.idTypeMatelas=1 order by m.dateMvtStock asc")
    public List<MvtStock> findMvtBloc();
}

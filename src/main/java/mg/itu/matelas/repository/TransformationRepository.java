package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.itu.matelas.dto.BeneficeDTO;
import mg.itu.matelas.entity.Transformation;

import java.util.List;

@Repository
public interface TransformationRepository extends JpaRepository<Transformation, Long> {
    @Query(value="with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,t.id_bloc as id_bloc,t.remarque " +
            "from transformation_produit as tp " +
            "natural join transformation as t " +
            "group by t.id_bloc, t.remarque) " +
            "select sum(prix_vente) as prix_vente,remarque,m.prix_unitaire as prix_revient,sum(prix_vente)-m.prix_unitaire as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire,remarque",nativeQuery=true)
    public List<BeneficeDTO> getBeneficeTheorique();
}

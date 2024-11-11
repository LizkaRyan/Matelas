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
    @Query(value="with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,m.prix_unitaire as prix_unitaire,t.remarque,t.id_bloc " +
            "               from transformation_produit as tp " +
            "                        natural join transformation as t join matelas as m on m.id_matelas=t.id_reste group by t.id_bloc,m.prix_unitaire,t.remarque) " +
            "select sum(prix_vente) as prix_vente,remarque,m.prix_unitaire-pv.prix_unitaire as prix_revient,sum(prix_vente)-(m.prix_unitaire-pv.prix_unitaire) as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire,remarque,pv.prix_unitaire",nativeQuery=true)
    public List<BeneficeDTO> getBeneficeTheorique();
}

package mg.itu.matelas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mg.itu.matelas.dto.BeneficeDTO;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.entity.id.IdTransformationProduit;

public interface TransformationProduitRepository extends JpaRepository<TransformationProduit, IdTransformationProduit> {
    @Query(value="with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,t.id_bloc as id_bloc  " + //
                "from transformation_produit as tp " + //
                "natural join transformation as t " + //
                "where tp.id_transformation=:idTransformation  " + //
                "group by t.id_bloc) " + //
                "select sum(prix_vente) as prix_vente,m.prix_unitaire,sum(prix_vente)-m.prix_unitaire as benefice_theorique "+
                "from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire",nativeQuery=true)
    public Optional<BeneficeDTO> getBeneficeTheorique(@Param("idTransformation") Long idTransformation);
}

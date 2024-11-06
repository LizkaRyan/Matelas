package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.entity.id.IdTransformationProduit;

@Repository
public interface TransformationProduitRepository extends JpaRepository<TransformationProduit, IdTransformationProduit> {
    
}

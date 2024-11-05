package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.entity.id.IdTransformationProduit;

public interface TransformationProduitRepository extends JpaRepository<TransformationProduit, IdTransformationProduit> {
    
}

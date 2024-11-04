package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.itu.matelas.entity.Transformation;

public interface TransformationRepository extends JpaRepository<Transformation, Long> {
    
}

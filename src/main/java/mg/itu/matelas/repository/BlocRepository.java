package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.itu.matelas.entity.Bloc;

public interface BlocRepository extends JpaRepository<Bloc, Long> {
    
}

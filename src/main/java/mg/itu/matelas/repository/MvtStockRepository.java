package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.itu.matelas.entity.MvtStock;

@Repository
public interface MvtStockRepository extends JpaRepository<MvtStock, Long> {
    
}

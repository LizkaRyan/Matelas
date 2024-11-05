package mg.itu.matelas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.itu.matelas.entity.MvtStock;

public interface MvtStockRepository extends JpaRepository<MvtStock, Long> {
    
}

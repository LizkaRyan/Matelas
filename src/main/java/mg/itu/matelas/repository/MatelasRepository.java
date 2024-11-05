package mg.itu.matelas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mg.itu.matelas.entity.Matelas;

public interface MatelasRepository extends JpaRepository<Matelas, Long> {
    @Query("select m from Matelas m where m.longeur=:longueur and m.largeur=:largeur and m.epaisseur=:epaisseur")
    public Optional<Matelas> findByDimension(@Param("longueur") double longueur,@Param("largeur") double largeur,@Param("epaisseur") double epaisseur);
}

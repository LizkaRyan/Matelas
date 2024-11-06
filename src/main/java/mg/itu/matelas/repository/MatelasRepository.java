package mg.itu.matelas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.itu.matelas.entity.Matelas;

@Repository
public interface MatelasRepository extends JpaRepository<Matelas, Long> {
    @Query("select m from Matelas m where m.longueur=:longueur and m.largeur=:largeur and m.epaisseur=:epaisseur")
    public Optional<Matelas> findByDimension(@Param("longueur") double longueur,@Param("largeur") double largeur,@Param("epaisseur") double epaisseur);

    @Query("select m from Matelas m join m.typeMatelas t where t.typeMatelas='Usuel'")
    public List<Matelas> findFormUsuel();

    @Query("select m from Matelas m join m.typeMatelas t where t.typeMatelas='Bloc' and m.etat=2")
    public List<Matelas> findBloc();

    @Query("select m from Matelas m join m.typeMatelas t where t.typeMatelas='Reste' and m.etat=2")
    public List<Matelas> findReste();
}

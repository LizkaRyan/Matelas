package mg.itu.matelas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="mvt_stock")
public class MvtStock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_mvt_stock")
    private Long idTypeMatelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_matelas")
    private Matelas matelas;
    
    private int entree;
    private int sortie;
    
    @Column(name="prix_unitaire")
    private double prixUnitaire;
}

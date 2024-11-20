package mg.itu.matelas.entity.fabrication;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "mvt_stock_matiere")
public class MvtStockMatiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mvt_stock_matiere")
    private Long idMvtStockMatiere;

    private double quantite;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;

    @Column(name = "date_mvt")
    private LocalDate dateMvt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere_premiere")
    private MatierePremiere matierePremiere;

    @Transient
    private double quantiteClone;

    public void setQuantiteClone(double quantite){
        this.quantiteClone=quantite;
    }

    public void setQuantite(double quantite){
        this.quantite=quantite;
        this.setQuantiteClone(quantite);
    }
}

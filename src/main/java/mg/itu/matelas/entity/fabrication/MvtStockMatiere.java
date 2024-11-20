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
    private Double quantiteClone;

    public void setQuantiteClone(Double quantite){
        this.quantiteClone=quantite;
    }

    public void setQuantite(Double quantite){
        this.quantite=quantite;
        this.setQuantiteClone(quantite);
    }

    public MvtStockMatiere(){}

    public MvtStockMatiere(
            Long idMvtStockMatiere,
            double quantite,
            double prixUnitaire,
            LocalDate dateMvt,
            MatierePremiere matierePremiere){
        this.setIdMvtStockMatiere(idMvtStockMatiere);
        this.setQuantite(quantite);
        this.setDateMvt(dateMvt);
        this.setMatierePremiere(matierePremiere);
        this.setPrixUnitaire(prixUnitaire);
    }
}

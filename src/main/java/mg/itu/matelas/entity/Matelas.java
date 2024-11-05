package mg.itu.matelas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mg.itu.matelas.other.ConstanteEtat;

@Entity
@Data
public class Matelas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_matelas")
    private Long idMatelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_type_matelas")
    private TypeMatelas typeMatelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_origine")
    private Matelas origine;

    private double longueur;
    private double largeur;
    private double epaisseur;

    @Column(name="prix_unitaire")
    private double prixUnitaire;

    private int etat=ConstanteEtat.NON_UTILISE;
}

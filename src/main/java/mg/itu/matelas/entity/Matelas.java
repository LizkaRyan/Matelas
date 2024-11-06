package mg.itu.matelas.entity;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mg.itu.matelas.dto.TransformationDTO;
import mg.itu.matelas.other.ConstanteEtat;
import mg.itu.matelas.other.ViewEntity;

@Entity
@Data
public class Matelas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_matelas")
    @JsonView({ViewEntity.Public.class})
    private Long idMatelas;

    String matelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_type_matelas")
    private TypeMatelas typeMatelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_origine")
    private Matelas origine;

    @JsonView({ViewEntity.Public.class})
    private double longueur;
    @JsonView({ViewEntity.Public.class})
    private double largeur;
    @JsonView({ViewEntity.Public.class})
    private double epaisseur;

    @Column(name="prix_unitaire")
    @JsonView({ViewEntity.Public.class})
    private double prixUnitaire;

    @JsonView({ViewEntity.Public.class})
    private int etat=ConstanteEtat.NON_UTILISE;

    @JsonView({ViewEntity.Public.class})
    public double getVolume(){
        return longueur*largeur*epaisseur;
    }

    @JsonView({ViewEntity.Public.class})
    public double getRapportVolume(){
        return this.prixUnitaire/this.getVolume();
    }

    public double getPrixUnitaireByOrigine(Matelas origine){
        return this.getVolume()*origine.getPrixUnitaire()/origine.getVolume();
    }

    public void setReste(TransformationDTO transformationDTO,Matelas origine){
        this.setOrigine(origine);
        this.setMatelas("Reste bloc "+origine.getIdMatelas());
        this.setEpaisseur(transformationDTO.getEpaisseurReste());
        this.setLongueur(transformationDTO.getLongueurReste());
        this.setLargeur(transformationDTO.getLargeurReste());
        this.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
        this.setPrixUnitaire(this.getPrixUnitaireByOrigine(origine));
        this.setEtat(ConstanteEtat.NON_UTILISE);
    }
}

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

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
public class Matelas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_matelas")
    @JsonView({ViewEntity.Public.class})
    private Long idMatelas;

    @JsonView({ViewEntity.Public.class})
    String matelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_type_matelas")
    private TypeMatelas typeMatelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_origine")
    private Matelas origine;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_ancestor")
    private Matelas ancestor;

    @JsonView({ViewEntity.Public.class})
    private float longueur;
    @JsonView({ViewEntity.Public.class})
    private float largeur;
    @JsonView({ViewEntity.Public.class})
    private float epaisseur;

    @Column(name="prix_unitaire")
    @JsonView({ViewEntity.Public.class})
    private float prixUnitaire;

    @JsonView({ViewEntity.Public.class})
    private int etat=ConstanteEtat.NON_UTILISE;

    @JsonView({ViewEntity.Public.class})
    public float getVolume(){
        return longueur*largeur*epaisseur;
    }

    @JsonView({ViewEntity.Public.class})
    public double getRapportVolume(){
        double valeur=this.prixUnitaire/this.getVolume();
        BigDecimal bd = new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public float getPrixUnitaireByOrigine(Matelas origine){
        return this.getVolume()*origine.getPrixUnitaire()/origine.getVolume();
    }

    public void setLongueur(float longueur)throws RuntimeException{
        if(longueur<=0){
            throw new RuntimeException("Longueur negatif ou nulle ne peut pas etre accepte");
        }
        this.longueur=longueur;
    }
    public void setLargeur(float largeur)throws RuntimeException{
        if(largeur<=0){
            throw new RuntimeException("Largeur negatif ou nulle ne peut pas etre accepte");
        }
        this.largeur=largeur;
    }
    public void setEpaisseur(float epaisseur)throws RuntimeException{
        if(epaisseur<=0){
            throw new RuntimeException("Epaisseur negatif ou nulle ne peut pas etre accepte");
        }
        this.epaisseur=epaisseur;
    }

    public void setPrixUnitaire(float prixUnitaire)throws RuntimeException{
        if(prixUnitaire<=0){
            throw new RuntimeException("Prix unitaire negaitif ou nulle ne peut pas etre accepte");
        }
        this.prixUnitaire=prixUnitaire;
    }

    public void setReste(TransformationDTO transformationDTO,Matelas origine)throws Exception{
        this.setOrigine(origine);
        Matelas ancetre=origine;
        if(origine.getAncestor()!=null){
            ancetre=origine.getAncestor();
        }
        this.setAncestor(ancetre);
        this.setMatelas("Reste bloc "+origine.getIdMatelas());
        this.setEpaisseur(transformationDTO.getEpaisseurReste());
        this.setLongueur(transformationDTO.getLongueurReste());
        this.setLargeur(transformationDTO.getLargeurReste());
        this.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
        this.setPrixUnitaire(this.getPrixUnitaireByOrigine(origine));
        this.setEtat(ConstanteEtat.NON_UTILISE);
    }
}

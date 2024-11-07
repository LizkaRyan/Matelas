package mg.itu.matelas.entity;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import mg.itu.matelas.entity.id.IdTransformationProduit;
import mg.itu.matelas.other.ViewEntity;

@Entity
@Data
@Table(name="transformation_produit")
public class TransformationProduit {
    @EmbeddedId
    private IdTransformationProduit id=new IdTransformationProduit();

    @MapsId("idProduit")
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonView({ViewEntity.Full.class})
    @JoinColumn(name="id_produit",insertable=false,updatable=false)
    private Matelas produit;

    @MapsId("idTransformation")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_transformation",insertable=false,updatable=false)
    private Transformation transformation;

    @Column(name="prix_unitaire")
    @JsonView({ViewEntity.Public.class})
    private double prixUnitaire;

    @JsonView({ViewEntity.Public.class})
    private int nombre;

    public void setMatelas(Matelas matelas){
        this.produit=matelas;
        this.id.setIdProduit(matelas.getIdMatelas());
    }

    public void setNombre(int nombre)throws RuntimeException{
        if(nombre<0){
            throw new RuntimeException("Nombre négatif ne peut pas etre accepte");
        }
        this.nombre=nombre;
    }

    public void setPrixUnitaire(double prixUnitaire){
        if(prixUnitaire<=0){
            throw new RuntimeException("Les prix unitaires ne peuvent pas etre negatif ou nulle");
        }
        this.prixUnitaire=prixUnitaire;
    }

    public void setTransformation(Transformation transformation){
        this.transformation=transformation;
        this.id.setIdTransformation(transformation.getIdTransformation());
    }
}

package mg.itu.matelas.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mg.itu.matelas.entity.id.IdTransformationProduit;

@Entity
@Data
public class TransformationProduit {
    @EmbeddedId
    private IdTransformationProduit idTransformationProduit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_produit")
    private Matelas produit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_transformation")
    private Transformation transformation;

    private int nombre;
}

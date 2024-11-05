package mg.itu.matelas.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import mg.itu.matelas.entity.id.IdTransformationProduit;

@Entity
@Data
public class TransformationProduit {
    @EmbeddedId
    private IdTransformationProduit idTransformationProduit;
}

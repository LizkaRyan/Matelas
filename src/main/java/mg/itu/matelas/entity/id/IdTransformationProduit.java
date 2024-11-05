package mg.itu.matelas.entity.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@EqualsAndHashCode
public class IdTransformationProduit implements Serializable {
    private Long idProduit;
    private Long idTransformation;
}

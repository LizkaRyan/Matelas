package mg.itu.matelas.dto;

import java.util.List;

import lombok.Data;

@Data
public class TransformationDTO {
    private Long idBloc;
    private int nombre;
    private List<TransformationProduitDTO> transformationProduit;
}

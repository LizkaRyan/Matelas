package mg.itu.matelas.dto;

import java.util.List;

import lombok.Data;

@Data
public class TransformationDTO {
    private Long idBloc;
    private List<TransformationProduitDTO> transformationProduits;
    private float longueurReste;
    private float largeurReste;
    private float epaisseurReste;

    public double getVolumeReste(){
        return longueurReste*largeurReste*epaisseurReste;
    }
}

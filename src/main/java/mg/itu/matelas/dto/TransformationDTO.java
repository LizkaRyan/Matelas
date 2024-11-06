package mg.itu.matelas.dto;

import java.util.List;

import lombok.Data;

@Data
public class TransformationDTO {
    private Long idBloc;
    private List<TransformationProduitDTO> transformationProduit;
    private double longueurReste;
    private double largeurReste;
    private double epaisseurReste;

    public double getVolumeReste(){
        return longueurReste*largeurReste*epaisseurReste;
    }
}

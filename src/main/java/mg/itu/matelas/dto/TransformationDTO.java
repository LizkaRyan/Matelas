package mg.itu.matelas.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TransformationDTO {
    private Long idBloc;
    private List<TransformationProduitDTO> transformationProduits;
    private float longueurReste;
    private int longueurMetrage;
    private float largeurReste;
    private int largeurMetrage;
    private float epaisseurReste;
    private int epaisseurMetrage;
    private LocalDate dateTransformation;
    private String remarque;

    public double getVolumeReste(){
        return longueurReste*largeurReste*epaisseurReste;
    }
}

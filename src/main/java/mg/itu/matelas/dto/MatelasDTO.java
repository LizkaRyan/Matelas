package mg.itu.matelas.dto;

import lombok.Data;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.TypeMatelas;

@Data
public class MatelasDTO {
    private String matelas;
    private float longueur;
    private float largeur;
    private float epaisseur;
    private float prixUnitaire;

    public Matelas createMatelas()throws Exception{
        Matelas matelas=new Matelas();
        matelas.setMatelas(this.matelas);
        matelas.setLongueur(longueur);
        matelas.setLargeur(largeur);
        matelas.setEpaisseur(epaisseur);
        matelas.setPrixUnitaire(prixUnitaire);
        matelas.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
        return matelas;
    }
}

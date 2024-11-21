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
import mg.itu.matelas.dto.Metrage;
import mg.itu.matelas.dto.TransformationDTO;
import mg.itu.matelas.other.ConstanteEtat;
import mg.itu.matelas.other.POV;
import mg.itu.matelas.utils.Utilitaire;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Matelas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_matelas")
    @JsonView({POV.Public.class})
    private Long idMatelas;

    @JsonView({POV.Public.class})
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

    @JsonView({POV.Public.class})
    private double longueur;
    @JsonView({POV.Public.class})
    private double largeur;
    @JsonView({POV.Public.class})
    private double epaisseur;

    @Column(name="prix_unitaire")
    @JsonView({POV.Public.class})
    private double prixUnitaire;

    @JsonView({POV.Public.class})
    private int etat=ConstanteEtat.NON_UTILISE;

    public Matelas(){

    }
    public Matelas(double prixRevientGlobal,float pourcentage){
        this.setLongueur(Utilitaire.generateNumberRand(5,7));
        this.setLargeur(Utilitaire.generateNumberRand(20,25));
        this.setEpaisseur(Utilitaire.generateNumberRand(10,15));
        double prixRevient=prixRevientGlobal*Utilitaire.generateNumberRand(-pourcentage,pourcentage)/100f;
        this.setPrixUnitaire(prixRevientGlobal+prixRevient);
        this.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
    }

    public Matelas(String longueur,String largeur,String epaisseur,String prixRevient){
        this.setLongueur(Utilitaire.parseDouble(longueur));
        this.setLargeur(Utilitaire.parseDouble(largeur));
        this.setEpaisseur(Utilitaire.parseDouble(epaisseur));
        this.setPrixUnitaire(Utilitaire.parseDouble(prixRevient));
    }

    private void setRandLongueur(float min,float max){
    }

    private void setRandLargeur(float min,float max){
        this.setLargeur(Utilitaire.generateNumberRand(min,max));
    }

    private void setRandEpaisseur(float min,float max){
        this.setEpaisseur(Utilitaire.generateNumberRand(min,max));
    }

    @JsonView({POV.Public.class})
    public double getVolume(){
        return longueur*largeur*epaisseur;
    }

    @JsonView({POV.Public.class})
    public double getRapportVolume(){
        double valeur=this.prixUnitaire/this.getVolume();
        System.out.println("Division :"+this.prixUnitaire+"/"+this.getVolume()+" = "+valeur);
        BigDecimal bd = new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double getPrixUnitaireByOrigine(Matelas origine){
        return this.getVolume()*origine.getPrixUnitaire()/origine.getVolume();
    }

    public void setLongueur(double longueur)throws RuntimeException{
        if(longueur<=0){
            throw new RuntimeException("Longueur negatif ou nulle ne peut pas etre accepte");
        }
        this.longueur=longueur;
    }
    public void setLargeur(double largeur)throws RuntimeException{
        if(largeur<=0){
            throw new RuntimeException("Largeur negatif ou nulle ne peut pas etre accepte");
        }
        this.largeur=largeur;
    }
    public void setEpaisseur(double epaisseur)throws RuntimeException{
        if(epaisseur<=0){
            throw new RuntimeException("Epaisseur negatif ou nulle ne peut pas etre accepte");
        }
        this.epaisseur=epaisseur;
    }

    public void setPrixUnitaire(double prixUnitaire)throws RuntimeException{
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
        System.out.println(transformationDTO.getEpaisseurMetrage()+" metrage epaisseur");
        System.out.println(transformationDTO.getLongueurMetrage()+" metrage longueur");
        System.out.println(transformationDTO.getLargeurMetrage()+" metrage largeur");
        Metrage metrageLongueur=new Metrage();
        Metrage metrageEpaisseur=new Metrage(transformationDTO.getEpaisseurReste(),transformationDTO.getEpaisseurMetrage());
        Metrage metrageLargeur=new Metrage(transformationDTO.getLargeurReste(),transformationDTO.getLargeurMetrage());
        metrageLongueur.setMetrage(transformationDTO.getLongueurMetrage());
        metrageLargeur.setMetrage(transformationDTO.getLargeurMetrage());
        metrageEpaisseur.setMetrage(transformationDTO.getEpaisseurMetrage());
        metrageEpaisseur.setValue(transformationDTO.getEpaisseurReste());
        metrageLargeur.setValue(transformationDTO.getLargeurReste());
        metrageLongueur.setValue(transformationDTO.getLongueurReste());
        this.setEpaisseur((float)metrageEpaisseur.convert());
        this.setLongueur((float)metrageLongueur.convert());
        this.setLargeur((float)metrageLargeur.convert());
        this.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
        this.setPrixUnitaire(this.getPrixUnitaireByOrigine(origine));
        this.setEtat(ConstanteEtat.NON_UTILISE);
    }

    public static List<Matelas> init(){
        List<Matelas> matelas=new ArrayList<Matelas>();
        Matelas matela=new Matelas();
        matela.setLongueur(3);
        matela.setLargeur(23.21f);
        matela.setEpaisseur(13.12f);
        matela.setPrixUnitaire(1000000);
        matela.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
        matelas.add(matela);
        matela=new Matelas();
        matela.setLongueur(2.18f);
        matela.setLargeur(24.44f);
        matela.setEpaisseur(12.99f);
        matela.setPrixUnitaire(2500000);
        matela.setTypeMatelas(new TypeMatelas(1l,"Bloc"));
        matelas.add(matela);
        return matelas;
    }

    public static double getMoyennePRU(List<Matelas> matelas){
        double answer=0;
        for (Matelas matela:matelas) {
            answer+=matela.getPrixUnitaire();
        }
        return answer/matelas.size();
    }
}

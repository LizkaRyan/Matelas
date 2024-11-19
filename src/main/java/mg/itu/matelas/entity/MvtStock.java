package mg.itu.matelas.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;
import mg.itu.matelas.entity.fabrication.Formule;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import mg.itu.matelas.other.POV;
import mg.itu.matelas.utils.Utilitaire;

@Entity
@Data
@Table(name="mvt_stock")
public class MvtStock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_mvt_stock")
    @JsonView({POV.Public.class})
    private Long idMvtStock;

    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="id_matelas")
    @JsonView({POV.Full.class})
    private Matelas matelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_transformation")
    @JsonView({POV.Full.class})
    private Transformation transformation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_machine")
    @JsonView({POV.Full.class})
    private Machine machine;

    @JsonView({POV.Public.class})
    private int entree;
    @JsonView({POV.Public.class})
    private int sortie;

    @Column(name = "date_mvt_stock")
    @JsonView({POV.Public.class})
    private LocalDate dateMvtStock;
    
    @Column(name="prix_unitaire")
    @JsonView({POV.Public.class})
    private double prixUnitaire;

    @Column(name="prix_revient")
    @JsonView({POV.Public.class})
    private double prixRevient;

    @Transient
    private double prixRevientTheorique;

    public double getEcart(){
        return prixRevient-prixRevientTheorique;
    }

    public MvtStock(){

    }

    public MvtStock(Matelas bloc,Machine machine){
        this.setMatelas(bloc);
        this.setMachine(machine);
        this.setDateMvtStock(Utilitaire.generateDateRand(LocalDate.of(2022,1,1),LocalDate.of(2024,12,31)));
        this.setEntree(1);
    }

    public void setPrixRevientTheorique(HashMap<Long,List<MvtStockMatiere>> mvtStockMatieres, List<Formule> formules){
        for (Formule formule: formules) {
            Long idMatiere=formule.getMatierePremiere().getIdMatierePremiere();
            double qteVoulu=this.getMatelas().getVolume()*formule.getQuantite();
            this.setPrixRevientTheorique(mvtStockMatieres.get(idMatiere),formule,qteVoulu);
        }
    }

    public void setPrixRevientTheorique(List<MvtStockMatiere> mvtStockMatieres,Formule formule,double qteVoulu){
        MvtStockMatiere mvtStockMatiere=mvtStockMatieres.get(0);
        double quantiteMvtStockMatiere=mvtStockMatiere.getQuantite();
        if(quantiteMvtStockMatiere>qteVoulu) {
            mvtStockMatiere.setQuantite(quantiteMvtStockMatiere - qteVoulu);
            double prixRevient = qteVoulu * mvtStockMatiere.getPrixUnitaire();
            this.setPrixRevient(this.getPrixRevient() + prixRevient);
            return;
        }
        double prixRevient=qteVoulu * mvtStockMatiere.getPrixUnitaire();
        this.setPrixRevient(this.getPrixRevient()+prixRevient);
        qteVoulu=-mvtStockMatiere.getQuantite();
        mvtStockMatieres.remove(0);
        this.setPrixRevientTheorique(mvtStockMatieres,formule,qteVoulu);
    }

    public void setMatelas(Matelas bloc){
        this.matelas=bloc;
        this.setPrixRevient(bloc.getPrixUnitaire());
    }

    public MvtStock(TransformationProduit produit,Matelas bloc){
        this.setEntree(produit.getNombre());
        this.setSortie(0);
        this.setMatelas(produit.getProduit());
        this.setDateMvtStock(produit.getTransformation().getDateTransformation());
        this.setPrixUnitaire(produit.getProduit().getPrixUnitaire());
        this.setPrixRevient(produit.getProduit().getPrixUnitaireByOrigine(bloc));
    }

    public static MvtStock entreeBloc(Matelas matelas,LocalDate dateInsertion,Matelas origine){
        MvtStock mvtStock=new MvtStock();
        mvtStock.setEntree(1);
        mvtStock.setSortie(0);
        mvtStock.setMatelas(matelas);
        mvtStock.setDateMvtStock(dateInsertion);
        double prixRevient=matelas.getPrixUnitaire();
        if(origine!=null){
            prixRevient=matelas.getPrixUnitaireByOrigine(origine);
        }
        mvtStock.setPrixRevient(prixRevient);
        mvtStock.setPrixUnitaire(matelas.getPrixUnitaire());
        return mvtStock;
    }
    public static MvtStock sortieBloc(Matelas matelas,LocalDate dateInsertion){
        MvtStock mvtStock=new MvtStock();
        mvtStock.setEntree(0);
        mvtStock.setSortie(1);
        mvtStock.setMatelas(matelas);
        mvtStock.setDateMvtStock(dateInsertion);
        mvtStock.setPrixUnitaire(0);
        mvtStock.setPrixRevient(matelas.getPrixUnitaire());
        return mvtStock;
    }
}
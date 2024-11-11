package mg.itu.matelas.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import mg.itu.matelas.other.ViewEntity;

@Entity
@Data
@Table(name="mvt_stock")
public class MvtStock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_mvt_stock")
    @JsonView({ViewEntity.Public.class})
    private Long idMvtStock;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_matelas")
    @JsonView({ViewEntity.Full.class})
    private Matelas matelas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_transformation")
    @JsonView({ViewEntity.Full.class})
    private Transformation transformation;

    @JsonView({ViewEntity.Public.class})
    private int entree;
    @JsonView({ViewEntity.Public.class})
    private int sortie;

    @Column(name = "date_mvt_stock")
    @JsonView({ViewEntity.Public.class})
    private LocalDate dateMvtStock;
    
    @Column(name="prix_unitaire")
    @JsonView({ViewEntity.Public.class})
    private double prixUnitaire;

    @Column(name="prix_revient")
    @JsonView({ViewEntity.Public.class})
    private double prixRevient;

    public MvtStock(){

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
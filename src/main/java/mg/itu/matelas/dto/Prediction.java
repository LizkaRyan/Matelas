package mg.itu.matelas.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.other.ViewEntity;

@Data
public class Prediction implements Serializable{
    @JsonView(ViewEntity.Public.class)
    private Matelas bloc;
    @JsonView(ViewEntity.Public.class)
    private Matelas usuel;
    @JsonView(ViewEntity.Public.class)
    private int nombreCreer;
    @JsonView(ViewEntity.Public.class)
    private double volumeRestant;
    @JsonView(ViewEntity.Public.class)
    private double prixRapportVolume;

    protected void setNombreCreer(){
        double valeur=(this.bloc.getVolume()/this.usuel.getVolume());
        System.out.println("Valeur "+valeur);
        BigDecimal bd = new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP);
        this.setNombreCreer((int)bd.doubleValue());
    }
    
    protected void setVolumeRestant(){
        double valeur=this.bloc.getVolume()-(this.getNombreCreer()*this.usuel.getVolume());
        BigDecimal bd = new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP);
        this.setVolumeRestant(bd.doubleValue());
    }

    protected void setPrixRapportVolume(){
        double valeur=this.usuel.getRapportVolume();
        BigDecimal bd = new BigDecimal(valeur).setScale(2, RoundingMode.HALF_UP);
        this.setPrixRapportVolume(bd.doubleValue());
    }

    public Prediction(Matelas bloc,Matelas usuel){
        this.setBloc(bloc);
        this.setUsuel(usuel);
        this.setNombreCreer();
        this.setVolumeRestant();
        this.setPrixRapportVolume();
    }

    public static List<Prediction> getListPrediction(List<Matelas> usuels,Matelas bloc){
        List<Prediction> valiny=new ArrayList<Prediction>();
        for (Matelas usuel : usuels) {
            Prediction prediction=new Prediction(bloc,usuel);
            valiny.add(prediction);
        }
        return valiny;
    }

    public static Prediction getOptimiste(List<Matelas> usuels,Matelas bloc){
        List<Prediction> valiny=getListPrediction(usuels, bloc);
        return getOptimiste(valiny);
    }

    public static Prediction getMinPerte(List<Matelas> usuels,Matelas bloc){
        List<Prediction> valiny=getListPrediction(usuels, bloc);
        return getMinPerte(valiny);
    }

    public static Prediction getOptimiste(List<Prediction> predictions){
        Prediction valiny=null;
        double max=0;
        for(Prediction prediction : predictions){
            if(max<prediction.getPrixRapportVolume()){
                max=prediction.getPrixRapportVolume();
                valiny=prediction;
            }
        }
        return valiny;
    }

    public static Prediction getMinPerte(List<Prediction> predictions){
        Prediction valiny=null;
        double min=9999999;
        for(Prediction prediction : predictions){
            if(min>prediction.getVolumeRestant()){
                min=prediction.getVolumeRestant();
                valiny=prediction;
            }
        }
        return valiny;
    }
}

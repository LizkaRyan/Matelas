package mg.itu.matelas.dto;

import lombok.Data;

@Data
public class Metrage {
    double value=0;
    int metrage;
    public Metrage(double value,int metrage){
        this.setValue(value);
        this.setMetrage(metrage);
    }
    public Metrage(){
    }

    public double convert(){
        System.out.println(this.metrage+" "+value+" "+this.metrage=="m"+" valiny");
        if(this.metrage==0){
            return this.getValue();
        }
        return this.getValue()/100.0;
    }
}

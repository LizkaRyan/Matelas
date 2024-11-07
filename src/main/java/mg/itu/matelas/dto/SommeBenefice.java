package mg.itu.matelas.dto;

import java.util.List;

public class SommeBenefice implements BeneficeDTO{
    double prixVente;
    double prixRevient;
    double benefice;
    String remarque;
    @Override
    public double getPrixRevient() {
        return this.prixRevient;
    }

    @Override
    public double getPrixVente() {
        return this.prixVente;
    }

    @Override
    public double getBeneficeTheorique() {
        return this.benefice;
    }

    @Override
    public String getRemarque(){
        return this.remarque;
    }

    public static SommeBenefice sommer(List<BeneficeDTO> benefices){
        SommeBenefice sommeBenefice=new SommeBenefice();
        sommeBenefice.remarque="Somme";
        for (int i = 0; i < benefices.size(); i++) {
            sommeBenefice.prixVente+=benefices.get(i).getPrixVente();
            sommeBenefice.benefice+=benefices.get(i).getBeneficeTheorique();
            sommeBenefice.prixRevient+=benefices.get(i).getPrixRevient();
        }
        return sommeBenefice;
    }
}

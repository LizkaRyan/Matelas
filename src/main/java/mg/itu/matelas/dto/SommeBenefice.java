package mg.itu.matelas.dto;

import java.util.List;

public class SommeBenefice implements BeneficeDTO{
    double prixVente;
    double prixRevient;
    double benefice;
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

    public static SommeBenefice sommer(List<BeneficeDTO> benefices){
        SommeBenefice sommeBenefice=new SommeBenefice();
        for (int i = 0; i < benefices.size(); i++) {
            sommeBenefice.prixVente+=benefices.get(i).getPrixVente();
            sommeBenefice.benefice+=benefices.get(i).getBeneficeTheorique();
            sommeBenefice.prixRevient+=benefices.get(i).getBeneficeTheorique();
        }
        return sommeBenefice;
    }
}

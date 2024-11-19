package mg.itu.matelas.dto;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.matelas.other.POV;

public interface BeneficeDTO {
    @JsonView(POV.Full.class)
    double getPrixRevient();
    @JsonView(POV.Full.class)
    double getPrixVente();
    @JsonView(POV.Full.class)
    double getBeneficeTheorique();
    @JsonView(POV.Full.class)
    String getRemarque();
}

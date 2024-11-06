package mg.itu.matelas.dto;

import com.fasterxml.jackson.annotation.JsonView;

import mg.itu.matelas.other.ViewEntity;

public interface BeneficeDTO {
    @JsonView(ViewEntity.Full.class)
    double getPrixRevient();
    @JsonView(ViewEntity.Full.class)
    double getPrixVente();
    @JsonView(ViewEntity.Full.class)
    double getBeneficeTheorique();
}

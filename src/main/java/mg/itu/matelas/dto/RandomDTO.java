package mg.itu.matelas.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RandomDTO {
    int nombre;
    double longueurMin;
    double longueurMax;
    double largeurMin;
    double largeurMax;
    double epaisseurMin;
    double epaisseurMax;
    LocalDate dateMin;
    LocalDate dateMax;
    double pourcentage;
}

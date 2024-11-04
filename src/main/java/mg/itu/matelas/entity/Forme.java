package mg.itu.matelas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Forme {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_forme")
    private Long idForme;

    private double longueur;

    private double largeur;

    private double epaisseur;
}

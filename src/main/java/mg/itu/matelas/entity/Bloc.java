package mg.itu.matelas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bloc {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name="id_bloc")
    private Long idBloc;

    String bloc;

    @Column(name="prix_revient")
    double prixRevient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_forme")
    private Forme forme;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_origine")
    private Bloc origine;
}

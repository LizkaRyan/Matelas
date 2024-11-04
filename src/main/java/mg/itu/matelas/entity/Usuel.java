package mg.itu.matelas.entity;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import mg.itu.matelas.other.ViewEntity;

@Entity
public class Usuel {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name="id_usuel")
    @JsonView({ViewEntity.Public.class})
    private Long idUsuel;

    @JsonView({ViewEntity.Public.class})
    String usuel;

    @Column(name="prix_vente")
    @JsonView({ViewEntity.Full.class})
    double prixVente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_forme")
    @JsonView({ViewEntity.Full.class})
    private Forme forme;
}

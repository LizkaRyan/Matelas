package mg.itu.matelas.entity.fabrication;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Formule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formule")
    private Long idFormule;

    private float quantite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere_premiere")
    private MatierePremiere matierePremiere;
}

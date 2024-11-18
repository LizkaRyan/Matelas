package mg.itu.matelas.entity.fabrication;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "matiere_premiere")
public class MatierePremiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matiere_premiere")
    private Long idMatierePremiere;

    @Column(name = "matiere_premiere")
    private String matierePremiere;
}

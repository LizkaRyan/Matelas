package mg.itu.matelas.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Transformation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_transformation")
    private Long idTransformation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_bloc")
    private Matelas bloc;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matelas> produit;

    private int nombre;
}

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
public class Transformation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_transformation")
    private Long idTransformation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_bloc")
    private Bloc bloc;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuel")
    private Usuel usuel;
}

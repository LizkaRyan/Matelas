package mg.itu.matelas.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

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
import jakarta.persistence.Table;
import lombok.Data;
import mg.itu.matelas.other.ViewEntity;

@Entity
@Data
@Table(name="transformation")
public class Transformation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_transformation")
    @JsonView({ViewEntity.Public.class})
    private Long idTransformation;

    @JsonView({ViewEntity.Public.class})
    private String remarque;

    @Column(name="date_transformation")
    @JsonView({ViewEntity.Public.class})
    private LocalDate dateTransformation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_bloc")
    @JsonView({ViewEntity.Full.class})
    private Matelas bloc;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_reste")
    @JsonView({ViewEntity.Full.class})
    private Matelas reste;

    @JsonView({ViewEntity.Full.class})
    @OneToMany(mappedBy = "transformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransformationProduit> produit=new ArrayList<TransformationProduit>();
}

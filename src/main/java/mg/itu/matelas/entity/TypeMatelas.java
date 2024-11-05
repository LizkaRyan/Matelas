package mg.itu.matelas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="type_matelas")
public class TypeMatelas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_type_matelas")
    private Long idTypeMatelas;

    @Column(name="type_matelas")
    private String typeMatelas;
}

package mg.itu.matelas.entity.fabrication;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_machine")
    private Long idMachine;

    private String machine;
}

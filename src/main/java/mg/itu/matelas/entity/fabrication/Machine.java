package mg.itu.matelas.entity.fabrication;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import mg.itu.matelas.other.POV;

@Entity
@Data
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_machine")
    @JsonView({POV.Public.class})
    private Long idMachine;

    @JsonView({POV.Public.class})
    private String machine;

    @Transient
    @JsonView({POV.Public.class})
    private double ecart;

    @Transient
    private double prixRevient;

    @Transient
    private double prixRevientTheorique;

    @Transient
    private double quantite;

    public Machine(Long idMachine,String machine){
        this.setIdMachine(idMachine);
        this.setMachine(machine);
    }

    public Machine(){}

    public Machine(Long idMachine,String machine,double ecart,double prixRevient,double prixRevientTheorique,double quantite){
        this.setIdMachine(idMachine);
        this.setMachine(machine);
        this.setEcart(ecart);
        this.setPrixRevient(prixRevient);
        this.setPrixRevientTheorique(prixRevientTheorique);
        this.setQuantite(quantite);
    }
}

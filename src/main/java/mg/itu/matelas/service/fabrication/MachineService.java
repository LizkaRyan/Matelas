package mg.itu.matelas.service.fabrication;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.repository.fabrication.MachineRepo;
import mg.itu.matelas.service.MatelasService;
import mg.itu.matelas.service.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MachineService {
    private final MachineRepo machineRepo;

    public MachineService(MachineRepo machineRepo) {
        this.machineRepo = machineRepo;
    }

    @Transactional
    public List<Machine> findAll(){
        return machineRepo.findAll();
    }

    @Transactional
    public List<Machine> findAllWithEcart(){
        return machineRepo.findMachineWithEcart();
    }

    @Transactional
    public List<Machine> findAllWithEcartByAnnee(int annee){
        return machineRepo.findMachineWithEcartByAnnee(annee);
    }

    @Transactional
    public List<HashMap<String,String>> findTempTable(){
        return machineRepo.findTableTemp();
    }
}

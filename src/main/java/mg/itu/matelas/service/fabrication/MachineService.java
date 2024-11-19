package mg.itu.matelas.service.fabrication;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.repository.fabrication.MachineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {
    @Autowired
    private MachineRepo machineRepo;

    @Transactional
    public List<Machine> findAll(){
        return machineRepo.findAll();
    }

    @Transactional
    public List<Machine> findAllWithEcart(){
        return machineRepo.findMachineWithEcart();
    }
}

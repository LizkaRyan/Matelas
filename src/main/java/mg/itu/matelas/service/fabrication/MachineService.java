package mg.itu.matelas.service.fabrication;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.repository.fabrication.MachineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MachineService {
    @Autowired
    private MachineRepo machineRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    /*public void saveAll(List<HashMap<String,String>> temps){
        for (HashMap<String,String> temp:temps) {
            Matelas matelas=new Matelas(temp.get("longueur"),temp.get("largeur"),temp.get("epaisseur"),temp.get("prixRevient"));
            MvtStock mvtStock=new MvtStock(matelas,temp.get("idMachine"));
        }
    }

    public void updateEcart(List<MvtStock> mvtStocks){
        String sql = "insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,prix_revient,ecart,id_machine,id_matelas) set ecart=? where id_mvt_stock=?";
        jdbcTemplate.batchUpdate(sql, mvtStocks, 1000, (ps, mvtStock) -> {
            ps.setDouble(1, mvtStock.getEcart());
            ps.setLong(2, mvtStock.getIdMvtStock());
        });
    }*/
}

package mg.itu.matelas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mg.itu.matelas.service.fabrication.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.dto.MatelasDTO;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.repository.MatelasRepository;

@Service
public class MatelasService {
    private final MatelasRepository matelasRepository;

    private final MvtStockService mvtStockService;

    private final MachineService machineService;

    private final JdbcTemplate jdbcTemplate;

    public MatelasService(JdbcTemplate jdbcTemplate, MatelasRepository matelasRepository, MvtStockService mvtStockService, MachineService machineService) {
        this.jdbcTemplate = jdbcTemplate;
        this.matelasRepository = matelasRepository;
        this.mvtStockService = mvtStockService;
        this.machineService = machineService;
    }

    @Transactional
    public Matelas save(MatelasDTO matelasInserted)throws Exception{
        Matelas matelas=matelasInserted.createMatelas();
        matelas=matelasRepository.save(matelas);
        MvtStock mvtStock=MvtStock.entreeBloc(matelas,matelasInserted.getDateInsertion(),null);
        mvtStockService.save(mvtStock);
        return matelas;
    }

    public Matelas save(Matelas matelas)throws Exception{
        return matelasRepository.save(matelas);
    }

    public void saveMatelas(List<Matelas> matelas){
        String sql = "insert into matelas(longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_matelas) ";
        sql+="values(?,?,?,?,2,1)";
        jdbcTemplate.batchUpdate(sql, matelas, 1000, (ps, matela) -> {
            ps.setDouble(1, matela.getLongueur());
            ps.setDouble(2, matela.getLargeur());
            ps.setDouble(3, matela.getEpaisseur());
            ps.setDouble(4, matela.getPrixUnitaire());
            ps.setDouble(5, matela.getIdMatelas());
        });
    }

    public List<Matelas> findUsuel(){
        return matelasRepository.findFormUsuel();
    }
    public List<Matelas> findBloc(){
        return matelasRepository.findBloc();
    }

    public Matelas findById(Long id){
        return matelasRepository.findById(id).orElseThrow(()->new RuntimeException("Bloc non trouve"));
    }

    public void saveAll(){
        List<HashMap<String,String>> temps=machineService.findTempTable();
        List<Matelas> matelas=new ArrayList<Matelas>();
        List<MvtStock> mvtStocks=new ArrayList<MvtStock>();
        for (HashMap<String,String> temp:temps) {
            Matelas matela=new Matelas(temp.get("idMatelas"),temp.get("longueur"),temp.get("largeur"),temp.get("epaisseur"),temp.get("prixRevient"));
            matelas.add(matela);
            mvtStocks.add(new MvtStock(matela,temp.get("idMachine"),temp.get("date")));
        }
        this.saveMatelas(matelas);
        mvtStockService.saveMvtStock(mvtStocks);
    }
}

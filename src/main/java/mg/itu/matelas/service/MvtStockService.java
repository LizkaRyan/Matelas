package mg.itu.matelas.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mg.itu.matelas.dto.EtatStock;
import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.entity.fabrication.Formule;
import mg.itu.matelas.entity.fabrication.Machine;
import mg.itu.matelas.entity.fabrication.MvtStockMatiere;
import mg.itu.matelas.service.fabrication.MachineService;
import mg.itu.matelas.service.fabrication.MvtStockMatiereService;
import mg.itu.matelas.utils.Utilitaire;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mg.itu.matelas.entity.MvtStock;
import mg.itu.matelas.entity.Transformation;
import mg.itu.matelas.entity.TransformationProduit;
import mg.itu.matelas.repository.MvtStockRepository;

@Service
public class MvtStockService {
    private final MvtStockRepository mvtStockRepository;

    private final MachineService machineService;

    private final MvtStockMatiereService mvtStockMatiereService;

    private final JdbcTemplate jdbcTemplate;

    public MvtStockService(MachineService machineService, MvtStockRepository mvtStockRepository, MvtStockMatiereService mvtStockMatiereService, JdbcTemplate jdbcTemplate) {
        this.machineService = machineService;
        this.mvtStockRepository = mvtStockRepository;
        this.mvtStockMatiereService = mvtStockMatiereService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public MvtStock save(MvtStock mvtStock){
        return mvtStockRepository.save(mvtStock);
    }

    public void flush(){
        mvtStockRepository.flush();
    }

    @Transactional
    public List<MvtStock> findMvtBloc(){
        return mvtStockRepository.findMvtBloc();
    }

    public void updateMvtStockWithPrixRevientTheorique(List<Formule> formules){
        List<MvtStock> mvtStocks=this.findMvtBloc();
        Hashtable<Long,List<MvtStockMatiere>> listMvtStockMatiere=mvtStockMatiereService.findMvtStockMatiereGroupByMatiere();
        for (MvtStock mvtStock:mvtStocks) {
            mvtStock.setPrixRevientTheorique(listMvtStockMatiere,formules);
            //this.save(mvtStock);
        }
        updateEcart(mvtStocks);
    }

    public void updateEcart(List<MvtStock> mvtStocks){
        String sql = "update mvt_stock set ecart=? where id_mvt_stock=?";
        jdbcTemplate.batchUpdate(sql, mvtStocks, 1000, (ps, mvtStock) -> {
            ps.setDouble(1, mvtStock.getEcart());
            ps.setLong(2, mvtStock.getIdMvtStock());
        });
    }

    public void saveMvtStock(List<MvtStock> mvtStocks){
        String sql = "insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,prix_revient,ecart,id_machine,id_matelas) ";
        sql+="values(1,0,0,?,?,0,?,?)";
        jdbcTemplate.batchUpdate(sql, mvtStocks, 1000, (ps, mvtStock) -> {
            ps.setDate(1, Date.valueOf(mvtStock.getDateMvtStock()));
            ps.setDouble(2, mvtStock.getPrixRevient());
            ps.setLong(3, mvtStock.getIdMachine());
            ps.setLong(4,mvtStock.getMatelas().getIdMatelas());
        });
    }

    public void saveMatelasByMvtStock(List<MvtStock> mvtStocks){
        String sql = "insert into matelas(id_matelas,matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values" +
                     " (?,?,?,?,?,?,1,1,null)";
        jdbcTemplate.batchUpdate(sql, mvtStocks, 1000, (ps, mvtStock) -> {
            ps.setLong(1, mvtStock.getMatelas().getIdMatelas());
            ps.setString(2, mvtStock.getMatelas().getMatelas());
            ps.setDouble(3, mvtStock.getMatelas().getLongueur());
            ps.setDouble(4,mvtStock.getMatelas().getLargeur());
            ps.setDouble(5,mvtStock.getMatelas().getEpaisseur());
            ps.setDouble(6,mvtStock.getMatelas().getPrixUnitaire());
        });
    }

    @Transactional
    public List<MvtStock> save(Transformation transformation){
        List<MvtStock> mvtStocks=new ArrayList<MvtStock>();
        //Insertion mouvement stock produit
        for (TransformationProduit produits : transformation.getProduit()) {
            MvtStock mvtStock=new MvtStock(produits,transformation.getBloc());
            mvtStock.setTransformation(transformation);
            mvtStocks.add(mvtStockRepository.save(mvtStock));
        }
        //Insertion mouvement stock bloc
        MvtStock sortieBloc=MvtStock.sortieBloc(transformation.getBloc(),transformation.getDateTransformation());
        mvtStocks.add(mvtStockRepository.save(sortieBloc));
        return mvtStocks;
    }

    @Transactional
    public List<MvtStock> findAll(){
        return mvtStockRepository.findAll();
    }
    @Transactional
    public List<EtatStock> findEtatStock(){return mvtStockRepository.findEtatStock();}
}

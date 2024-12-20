with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,sum(tp.nombre*tp.prix_revient) as prix_achat,t.id_bloc as id_bloc,t.remarque
from transformation_produit as tp 
natural join transformation as t
group by t.id_bloc, t.remarque)
select sum(prix_vente) as prix_vente,remarque,prix_achat as prix_revient,sum(prix_vente)-prix_achat as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire,remarque,prix_achat;

select matelas,sum(entree*prix_revient)/sum(entree) as prix_revient,sum(entree) as etat from mvt_stock natural join matelas where id_type_matelas=2 and sortie=0 group by matelas;

with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,m.prix_unitaire as prix_unitaire,t.remarque,t.id_bloc
               from transformation_produit as tp
                        natural join transformation as t join matelas as m on m.id_matelas=t.id_reste group by t.id_bloc,m.prix_unitaire,t.remarque)
select sum(prix_vente) as prix_vente,remarque,m.prix_unitaire-pv.prix_unitaire as prix_revient,sum(prix_vente)-(m.prix_unitaire-pv.prix_unitaire) as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire,remarque,pv.prix_unitaire;

select sum(prix_revient) as prix_revient,sum(prix_revient-(ecart*longueur*largeur*epaisseur)) as prix_revient_theorique, avg(ecart) as ecart,machine.machine,sum(longueur*largeur*epaisseur) as quantite from mvt_stock as mv natural join machine join matelas as ma on ma.id_matelas=mv.id_matelas where extract(year from mv.date_mvt_stock)=2023 group by id_machine,machine.machine;
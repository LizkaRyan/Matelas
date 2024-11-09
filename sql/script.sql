with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,sum(tp.nombre*tp.prix_revient) as prix_achat,t.id_bloc as id_bloc,t.remarque
from transformation_produit as tp 
natural join transformation as t
group by t.id_bloc, t.remarque)
select sum(prix_vente) as prix_vente,remarque,prix_achat as prix_revient,sum(prix_vente)-prix_achat as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire,remarque,prix_achat;

select matelas,sum(entree*prix_revient)/sum(entree) as prix_revient,sum(entree) as etat from mvt_stock natural join matelas where id_type_matelas=2 and sortie=0 group by matelas;


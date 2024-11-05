with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,t.id_bloc as id_bloc 
from transformation_produit as tp 
natural join transformation as t
where tp.id_transformation=1 
group by t.id_bloc)
select sum(prix_vente) as prix_vente,m.prix_unitaire,sum(prix_vente)-m.prix_unitaire as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire;
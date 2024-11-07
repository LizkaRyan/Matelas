with vente as (select sum(tp.nombre*tp.prix_unitaire) as prix_vente,sum(tp.nombre*tp.prix_revient) as prix_achat,t.id_bloc as id_bloc,t.remarque
from transformation_produit as tp 
natural join transformation as t
group by t.id_bloc, t.remarque)
select sum(prix_vente) as prix_vente,remarque,prix_achat as prix_revient,sum(prix_vente)-prix_achat as benefice_theorique from vente as pv join matelas as m on m.id_matelas=pv.id_bloc group by id_matelas,m.prix_unitaire,remarque,prix_achat;

select sum(entree)-sum(sortie) as etat, matelas from mvt_stock natural join matelas group by id_matelas,matelas;

CREATE OR REPLACE FUNCTION updatePriceRevient()
RETURNS Trigger AS $$
DECLARE
    percent numeric(15,5);
    fille RECORD;
    reussi integer;
BEGIN
    percent:=getPercent(OLD.prix_unitaire,NEW.prix_unitaire);
    FOR fille IN (select * from matelas where id_origine=NEW.id_matelas)
    LOOP
        reussi:=updateFille(fille.id_matelas,percent);
    END LOOP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getPondere(idBloc BIGINT)
RETURNS numeric(15,5) AS $$
DECLARE
    transformation RECORD;
    idTransformation BIGINT;
    pondere numeric(15,5);
    coef numeric(15,5);
BEGIN
    coef:=0;
    pondere:=0;
    select id_transformation into idTransformation from transformation where id_bloc=idBloc;
    FOR transformation IN (select * from transformation_produit where id_transformation=idTransformation)
    LOOP
        coef:=coef+transformation.nombre;
        pondere:=pondere+(transformation.nombre*transformation.prix_unitaire);
    END LOOP;
    IF coef = 0 THEN
        return 0;
    END IF;
    return pondere/coef;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getPercent(prix_avant NUMERIC(12,2),prix_apres NUMERIC(12,2))
RETURNS numeric(12,2) AS $$
DECLARE
    pondere numeric(15,5);
BEGIN
return prix_apres*100/prix_avant;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateFille(id_fille BIGINT,pourcent NUMERIC(15,2))
RETURNS integer AS $$
DECLARE
    fille RECORD;
    new_price NUMERIC(12,2);
    transformation RECORD;
    status integer;
BEGIN
FOR fille IN (select * from matelas where id_matelas=id_fille)
    LOOP
        new_price:=fille.prix_unitaire*pourcent/100.0;
        update matelas set prix_unitaire=new_price where id_matelas=id_fille;
        update mvt_stock set prix_revient=new_price where id_matelas=id_fille;
        FOR transformation IN (select * from transformation where id_reste=id_fille)
            LOOP
                update transformation_produit set prix_revient=prix_revient*(pourcent/100.0) where id_transformation=transformation.id_transformation;
                status:=updateMvtStock(transformation.id_transformation,pourcent);
        END LOOP;
END LOOP;
return 0;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION updateMvtStock(id_transfo BIGINT,pourcent NUMERIC(15,2))
RETURNS integer AS $$
DECLARE
    transfo_prod RECORD;
    new_price NUMERIC(12,2);
BEGIN
FOR transfo_prod in (select * from transformation_produit join matelas on id_produit=id_matelas where id_transformation=id_transfo)
    LOOP
        new_price:=transfo_prod.prix_revient*pourcent/100;
        update mvt_stock set prix_revient=new_price where id_matelas=transfo_prod.id_produit;
END LOOP;
return 0;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_matelas_trigger
    AFTER update ON matelas
    FOR EACH ROW
    EXECUTE FUNCTION updatePriceRevient();
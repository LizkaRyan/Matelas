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
    transfo RECORD;
    cout_revient NUMERIC(12,2);
BEGIN
FOR transfo in (select * from transformation join matelas on id_bloc=id_matelas where transformation.id_transformation=id_transfo)
    LOOP
        cout_revient:=transfo.prix_unitaire/(transfo.longueur*transfo.largeur*transfo.epaisseur);
END LOOP;
FOR transfo_prod in (select * from transformation_produit join matelas on id_produit=id_matelas join transformation on transformation_produit.id_transformation=transformation.id_transformation where transformation.id_transformation=id_transfo)
    LOOP
        new_price:=cout_revient*transfo_prod.longueur*transfo_prod.largeur*transfo_prod.epaisseur;
        update mvt_stock set prix_revient=new_price where id_matelas=transfo_prod.id_produit and id_transformation=id_transfo;
END LOOP;
return 0;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_matelas_trigger
    AFTER update ON matelas
    FOR EACH ROW
    EXECUTE FUNCTION updatePriceRevient()

\COPY (select longueur,largeur,epaisseur,prix_revient,prix_revient-ecart as prix_theorique,ecart,date_mvt_stock,id_machine from mvt_stock join matelas on mvt_stock.id_matelas=matelas.id_matelas) TO 'C:\Users\ryrab\Desktop\Ryan\Etudes\mvt_stock.csv' DELIMITER ',' CSV HEADER;
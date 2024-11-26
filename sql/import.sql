CREATE TEMP TABLE temp_table (
    id_matelas serial,
    longueur numeric(15,2),
    largeur numeric(15,2),
    epaisseur numeric(15,2),
    prix_unitaire numeric(15,2),
    date_mvt_stock date,
    id_machine INTEGER,
    etat INTEGER default 2,
    id_type_matelas INTEGER default 1
);

\COPY temp_table(date_mvt_stock,longueur,largeur,epaisseur,prix_unitaire,id_machine) FROM 'C:\Users\ryrab\Desktop\Ryan\Etudes\S5\ArchitectureLogiciel\Matelas\matelas\Matelas\sql\donnee.csv' WITH (FORMAT CSV, DELIMITER ',', HEADER);

INSERT INTO matelas(id_matelas,matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas)
 SELECT id_matelas,'B'||id_matelas as matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas FROM temp_table;

INSERT INTO mvt_stock(entree,date_mvt_stock,prix_revient,id_machine,id_matelas)
 select 1 as entree,date_mvt_stock,matelas.prix_unitaire,id_machine,matelas.id_matelas from temp_table join matelas on temp_table.id_matelas=matelas.id_matelas;

update mvt_stock set sortie=0,prix_unitaire=0,ecart=0;

DROP TABLE temp_table;
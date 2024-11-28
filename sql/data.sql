insert into type_matelas(type_matelas) values('Bloc');
insert into type_matelas(type_matelas) values('Usuel');

insert into machine(machine)
values  ('Machine 1'),
        ('Machine 2'),
        ('Machine 3'),
        ('Machine 4');

insert into matiere_premiere(matiere_premiere)
values ('Essence'),
       ('Papier'),
       ('Durcisseur');

insert into formule(id_matiere_premiere,quantite)
values  (1,1),
        (2,1),
        (3,0.5);

insert into matelas(matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values
    ('Bloc 1',7,20,7, 1500000,1,1,null),
    ('Bloc 2',100,40,10, 1200000,1,1,null);

insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,id_matelas,prix_revient,ecart,id_machine) values(1,0,0,NOW(),1,1500000,0,1);
insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,id_matelas,prix_revient,ecart,id_machine) values(1,0,0,NOW(),2,1200000,0,2);

insert into mvt_stock_matiere(quantite,prix_unitaire,date_mvt,id_matiere_premiere)
values  (224727345,6000,'2024-01-01',1),
        (224727345,600,'2024-01-01',2),
        (112363672.5,550,'2024-01-01',3),
        (225233313,5950,'2023-01-01',1),
        (225233313,500,'2023-01-01',2),
        (112616656.5,500,'2023-01-01',3),
        (224974632,5900,'2022-01-01',1),
        (224974632,400,'2022-01-01',2),
        (112487316,450,'2022-01-01',3);

-- insert into transformation(id_bloc) values(1);

--insert into transformation_produit(id_transformation,id_produit,nombre,prix_unitaire) values
--    (1,2,16,20000),
--    (1,3,10,35000),
--    (1,4,25,32500),
--    (1,5,30,50);

-- COPY (select id_matelas,prix_revient-ecart as theorique from mvt_stock) TO 'C:\Users\ryrab\Desktop\Ryan\Etudes\S5\ArchitectureLogiciel\Matelas\matelas\Matelas' DELIMITER ',' CSV HEADER;

-- insert into matelas(matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values('Reste B1',2,3,0.5,31250,1,1,1);
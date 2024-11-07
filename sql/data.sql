insert into type_matelas(type_matelas) values('Bloc');
insert into type_matelas(type_matelas) values('Usuel');
insert into type_matelas(type_matelas) values('Reste');

insert into matelas(matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values
    ('Bloc 1',10.6,8.2,5, 38250000,1,1,null),
    ('Usuel 1',2,1.6,0.3,125000,1,2,null),
    ('Usuel 2',2,1.4,0.25,100000,1,2,null),
    ('Usuel 3',1.9,0.9,0.2,60000,1,2,null),
    ('Usuel 4',0.1,0.1,0.1,100,1,2,null);

insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,id_matelas,prix_revient) values(1,0,0,NOW(),1,38250000);

-- insert into transformation(id_bloc) values(1);

--insert into transformation_produit(id_transformation,id_produit,nombre,prix_unitaire) values
--    (1,2,16,20000),
--    (1,3,10,35000),
--    (1,4,25,32500),
--    (1,5,30,50);

-- insert into matelas(matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values('Reste B1',2,3,0.5,31250,1,1,1);
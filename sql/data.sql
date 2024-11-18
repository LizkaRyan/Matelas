insert into type_matelas(type_matelas) values('Bloc');
insert into type_matelas(type_matelas) values('Usuel');

insert into machine(machine)
values  ('Machine 1'),
        ('Machine 2'),
        ('Machine 3');

insert into matiere_premiere(matiere_premiere)
values ('Essence'),
       ('Papier'),
       ('Durcisseur');

insert into formule(id_matiere_premiere,quantite)
values  (1,2),
        (2,3),
        (3,0.5);

insert into matelas(matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values
    ('Bloc 1',100,20,10, 2000000,1,1,null),
    ('Bloc 2',100,40,10, 3000000,1,1,null),
    ('Usuel 1',16,4,2,20000,1,2,null),
    ('Usuel 2',10,7,1,12000,1,2,null),
    ('Usuel 3',5,1,1,600,1,2,null);

insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,id_matelas,prix_revient) values(1,0,0,NOW(),1,2000000);
insert into mvt_stock(entree,sortie,prix_unitaire,date_mvt_stock,id_matelas,prix_revient) values(1,0,0,NOW(),2,3000000);

-- insert into transformation(id_bloc) values(1);

--insert into transformation_produit(id_transformation,id_produit,nombre,prix_unitaire) values
--    (1,2,16,20000),
--    (1,3,10,35000),
--    (1,4,25,32500),
--    (1,5,30,50);

-- insert into matelas(matelas,longueur,largeur,epaisseur,prix_unitaire,etat,id_type_matelas,id_origine) values('Reste B1',2,3,0.5,31250,1,1,1);
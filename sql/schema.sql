CREATE TABLE type_matelas(
   id_type_matelas SERIAL,
   type_matelas VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_type_matelas),
   UNIQUE(type_matelas)
);

CREATE TABLE matiere_premiere(
   id_matiere_premiere SERIAL,
   matiere_premiere VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_matiere_premiere)
);

CREATE TABLE mvt_stock_matiere(
   id_mvt_stock_matiere SERIAL,
   quantite NUMERIC(15,2)   NOT NULL,
   prix_unitaire NUMERIC(15,2)  ,
   date_mvt DATE NOT NULL,
   id_matiere_premiere INTEGER NOT NULL,
   PRIMARY KEY(id_mvt_stock_matiere),
   FOREIGN KEY(id_matiere_premiere) REFERENCES matiere_premiere(id_matiere_premiere)
);

CREATE TABLE machine(
   id_machine SERIAL,
   machine VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_machine),
   UNIQUE(machine)
);

CREATE TABLE formule(
   id_formule SERIAL,
   quantite NUMERIC(15,2)   NOT NULL,
   id_matiere_premiere INTEGER NOT NULL,
   PRIMARY KEY(id_formule),
   FOREIGN KEY(id_matiere_premiere) REFERENCES matiere_premiere(id_matiere_premiere)
);

CREATE TABLE matelas(
   id_matelas SERIAL,
   matelas VARCHAR(50) ,
   longueur NUMERIC(12,2)   NOT NULL,
   largeur NUMERIC(12,2)   NOT NULL,
   epaisseur NUMERIC(12,2)   NOT NULL,
   prix_unitaire NUMERIC(12,2)   NOT NULL,
   etat INTEGER NOT NULL,
   id_ancestor INTEGER,
   id_origine INTEGER,
   id_type_matelas INTEGER NOT NULL,
   PRIMARY KEY(id_matelas),
   FOREIGN KEY(id_ancestor) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_origine) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_type_matelas) REFERENCES type_matelas(id_type_matelas)
);

CREATE TABLE transformation(
   id_transformation SERIAL,
   remarque VARCHAR(100) ,
   date_transformation DATE NOT NULL,
   id_reste INTEGER,
   id_bloc INTEGER NOT NULL,
   PRIMARY KEY(id_transformation),
   FOREIGN KEY(id_reste) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_bloc) REFERENCES matelas(id_matelas)
);

CREATE TABLE mvt_stock(
   id_mvt_stock SERIAL,
   entree INTEGER,
   sortie INTEGER,
   prix_unitaire NUMERIC(12,2)  ,
   date_mvt_stock DATE,
   prix_revient NUMERIC(12,2)  ,
   ecart NUMERIC(15,2)  ,
   id_machine INTEGER,
   id_transformation INTEGER,
   id_matelas INTEGER NOT NULL,
   PRIMARY KEY(id_mvt_stock),
   FOREIGN KEY(id_machine) REFERENCES machine(id_machine),
   FOREIGN KEY(id_transformation) REFERENCES transformation(id_transformation),
   FOREIGN KEY(id_matelas) REFERENCES matelas(id_matelas)
);

CREATE TABLE transformation_produit(
   id_produit INTEGER,
   id_transformation INTEGER,
   nombre INTEGER NOT NULL,
   prix_unitaire NUMERIC(12,2)   NOT NULL,
   prix_revient NUMERIC(12,2)   NOT NULL,
   PRIMARY KEY(id_produit, id_transformation),
   FOREIGN KEY(id_produit) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_transformation) REFERENCES transformation(id_transformation)
);
-- matelas 3

CREATE TABLE type_matelas(
   id_type_matelas SERIAL,
   type_matelas VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_type_matelas),
   UNIQUE(type_matelas)
);

CREATE TABLE matelas(
   id_matelas SERIAL,
   longueur NUMERIC(12,2)   NOT NULL,
   largeur NUMERIC(12,2)   NOT NULL,
   epaisseur NUMERIC(12,2)   NOT NULL,
   etat INTEGER NOT NULL,
   id_origine INTEGER,
   id_type_matelas INTEGER NOT NULL,
   PRIMARY KEY(id_matelas),
   UNIQUE(id_origine),
   FOREIGN KEY(id_origine) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_type_matelas) REFERENCES type_matelas(id_type_matelas)
);

CREATE TABLE transformation(
   id_transformation SERIAL,
   nombre INTEGER NOT NULL,
   id_produit INTEGER NOT NULL,
   id_bloc INTEGER NOT NULL,
   PRIMARY KEY(id_transformation),
   FOREIGN KEY(id_produit) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_bloc) REFERENCES matelas(id_matelas)
);

CREATE TABLE mvt_stock(
   id_mvt_stock SERIAL,
   entree INTEGER,
   sortie INTEGER,
   prix_unitaire NUMERIC(12,2)  ,
   id_matelas INTEGER NOT NULL,
   PRIMARY KEY(id_mvt_stock),
   FOREIGN KEY(id_matelas) REFERENCES matelas(id_matelas)
);

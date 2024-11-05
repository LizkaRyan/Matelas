CREATE TABLE type_matelas(
   id_type_matelas SERIAL,
   type_matelas VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_type_matelas),
   UNIQUE(type_matelas)
);

CREATE TABLE matelas(
   id_matelas SERIAL,
   matelas VARCHAR(50)  NOT NULL,
   longueur NUMERIC(12,2)   NOT NULL,
   largeur NUMERIC(12,2)   NOT NULL,
   epaisseur NUMERIC(12,2)   NOT NULL,
   prix_unitaire NUMERIC(12,2)   NOT NULL,
   etat INTEGER NOT NULL,
   id_origine INTEGER,
   id_type_matelas INTEGER NOT NULL,
   PRIMARY KEY(id_matelas),
   UNIQUE(id_origine),
   UNIQUE(matelas),
   FOREIGN KEY(id_origine) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_type_matelas) REFERENCES type_matelas(id_type_matelas)
);

CREATE TABLE transformation(
   id_transformation SERIAL,
   id_bloc INTEGER NOT NULL,
   PRIMARY KEY(id_transformation),
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

CREATE TABLE transformation_produit(
   id_produit INTEGER,
   id_transformation INTEGER,
   nombre INTEGER NOT NULL,
   prix_unitaire NUMERIC(12,2)   NOT NULL,
   PRIMARY KEY(id_produit, id_transformation),
   FOREIGN KEY(id_produit) REFERENCES matelas(id_matelas),
   FOREIGN KEY(id_transformation) REFERENCES transformation(id_transformation)
);

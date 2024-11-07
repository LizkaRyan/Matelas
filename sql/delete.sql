-- Désactiver temporairement les contraintes de clé étrangère
ALTER TABLE transformation_produit DROP CONSTRAINT transformation_produit_id_produit_fkey;
ALTER TABLE transformation_produit DROP CONSTRAINT transformation_produit_id_transformation_fkey;
ALTER TABLE mvt_stock DROP CONSTRAINT mvt_stock_id_matelas_fkey;
ALTER TABLE transformation DROP CONSTRAINT transformation_id_bloc_fkey;
ALTER TABLE transformation DROP CONSTRAINT transformation_id_reste_fkey;
ALTER TABLE matelas DROP CONSTRAINT matelas_id_origine_fkey;
ALTER TABLE matelas DROP CONSTRAINT matelas_id_ancestor_fkey;
ALTER TABLE matelas DROP CONSTRAINT matelas_id_type_matelas_fkey;

-- Supprimer les données des tables dans l'ordre des dépendances
DELETE FROM transformation_produit;
DELETE FROM mvt_stock;
DELETE FROM transformation;
DELETE FROM matelas;
DELETE FROM type_matelas;

-- Réactiver les contraintes de clé étrangère
ALTER TABLE matelas ADD CONSTRAINT matelas_id_origine_fkey FOREIGN KEY (id_origine) REFERENCES matelas (id_matelas);
ALTER TABLE matelas ADD CONSTRAINT matelas_id_type_matelas_fkey FOREIGN KEY (id_type_matelas) REFERENCES type_matelas (id_type_matelas);
ALTER TABLE transformation ADD CONSTRAINT transformation_id_reste_fkey FOREIGN KEY (id_reste) REFERENCES matelas (id_matelas);
ALTER TABLE transformation ADD CONSTRAINT transformation_id_bloc_fkey FOREIGN KEY (id_bloc) REFERENCES matelas (id_matelas);
ALTER TABLE mvt_stock ADD CONSTRAINT mvt_stock_id_matelas_fkey FOREIGN KEY (id_matelas) REFERENCES matelas (id_matelas);
ALTER TABLE transformation_produit ADD CONSTRAINT transformation_produit_id_produit_fkey FOREIGN KEY (id_produit) REFERENCES matelas (id_matelas);
ALTER TABLE transformation_produit ADD CONSTRAINT transformation_produit_id_transformation_fkey FOREIGN KEY (id_transformation) REFERENCES transformation (id_transformation);

ALTER SEQUENCE type_matelas_id_type_matelas_seq RESTART WITH 1;
ALTER SEQUENCE matelas_id_matelas_seq RESTART WITH 1;
ALTER SEQUENCE matelas_id_ancestor_seq RESTART WITH 1;
ALTER SEQUENCE transformation_id_transformation_seq RESTART WITH 1;
ALTER SEQUENCE mvt_stock_id_mvt_stock_seq RESTART WITH 1;
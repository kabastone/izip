insert into type_ressources(TP_CODE,TP_DESCRIPTION)
	values('Vehicue', '')
	,('Chauffeur', '');
	
	insert into ressources(RS_CODE,RS_LIBELLE,RS_ID_PARENT)
	values('DK4007AN', '4x4',1)
	,('KB', 'Kaba Stone',2);
	
insert into droits(DRT_CATEGORIE,DRT_NOM,DRT_URL)
	values ('TDR','TDR', '/pages/demande/liste.zul'),('TDR Validé','TDR Validé', '/pages/demande/listeValide.zul');
	
	
insert into groupes(GRP_NOM) 
	values('Administrateur'),('Utilisateur');
	
insert into groupes_droits(droits_DRT_ID,groupes_GRP_ID) 
	values(1,1),(1,2),(2,1),(2,2);
	
insert into entites(ENT_LIBELLE,ENT_REFERENCE)
	       values ('Direction service informatique', 'DSI'),
	       ('Comptabilite','Compta'),
	       ('Patrimoine','Patrimoine'),('DRH','DRH'),('DGA','DGA'),
	       ('Superviseur','Superviseur'),('Controleur','Controleur');
	       
	insert into fontions(FCT_LIBELLE,FCT_REFERENCE)
	values ('Chef Service informatique','CSI'),
	       ('Comptable','Comptable'),('Patrimoine','Patrimoine'),('DRH','DRH'),
	       ('DGA','DGA'),('Superviseur','Superviseur'),('Controleur','Controleur');
	       
	       insert into statuts(ST_CODE)
	       values('Cadre Superieur'),('Cadre'),('Agent'),('Non cadre');
	       
	       
	insert into utilisateurs(USR_NOM,USR_PRENOM,USR_LOGIN,USR_PWD,ENT_ID_PARENT,FCT_ID_PARENT,ST_ID_PARENT)
	values('Sar','Kader','Admin','passer',1,1,1),
	('Camara','Ndiogou','ndiogou','passer',1,1,1),
	('','','patrimoine','passer',3,3,2),('','','drh','passer',4,4,2),
	('','','dga','passer',5,5,1),('','','comptable','passer',2,2,1),
	('','','superviseur','passer',6,6,1),('','','controleur','passer',7,7,2);
	
	insert into utilisateurs_groupes values (1,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),
	                                         (7,2);
	                                         
    insert into depenses (D_LIBELLE)
	            values('Remboursement de Frais'),
	                  ('Frais de mission'),
	                  ('Achat de fourniture et petit materiel'),
	                  ('Achat immobilisations'),
	                  ('Formation'),
	                  ('Autres');
	
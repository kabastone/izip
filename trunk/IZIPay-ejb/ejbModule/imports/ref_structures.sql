insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code) 
	values ('RESEAU', 1 , null),('OPERATEUR',1,'RESEAU'),
			('DISTRIBUTEUR',0,'OPERATEUR'),('AGENCE',0,'DISTRIBUTEUR'),
			('CAISSE',1,'AGENCE');

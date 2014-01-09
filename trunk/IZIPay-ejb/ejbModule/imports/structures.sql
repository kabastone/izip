insert into structures(STRUCT_ID, STRUCT_TS_CODE, STRUCT_PARENT_ID, STRUCT_VIRTUAL, STRUCT_LIBELLE, STRUCT_INTERNAL) 
	values(1, 'RESEAU', null, 0, 'Réseau A', 0),(2, 'OPERATEUR', 1, 0, 'OP 1', 0),(3, 'OPERATEUR', 1, 0, 'OP 2', 0),
		(4, 'DISTRIBUTEUR', 2, 1, 'DIST 1 OP 1', 0),(5, 'DISTRIBUTEUR', 3, 0, 'DIST 2 OP 2', 0),(6, 'DISTRIBUTEUR', 3, 0, 'DIST 3 OP 2', 0),
		(7, 'AGENCE', 4, 0, 'AG 1 DIST 1', 1),(8, 'AGENCE', 5, 0, 'AG 2 DIST 2', 1),(9, 'AGENCE', 6, 0, 'AG 3 DIST 3', 0),
		(10, 'CAISSE', 7, 0, 'CA 1 AG 1', 1),(11, 'CAISSE', 7, 0, 'CA 2 AG1', 1);
		
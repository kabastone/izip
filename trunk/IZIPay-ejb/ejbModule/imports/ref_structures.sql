insert into types_structures(ts_code,ts_autonome_allowed) values ('RESEAU',1);
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code) values ('OPERATEUR',1,'RESEAU');
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code,ts_virtual_allowed) values ('DISTRIBUTEUR',0,'OPERATEUR',1);
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code) values ('AGENCE',0,'DISTRIBUTEUR');
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code) values ('GUICHET',1,'AGENCE');

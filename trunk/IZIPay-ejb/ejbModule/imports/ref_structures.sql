insert into types_structures(ts_code,ts_autonome_allowed, ts_rang) values ('RESEAU',1,1);
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code, ts_rang) values ('OPERATEUR',1,'RESEAU',2);
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code,ts_virtual_allowed, ts_rang) values ('DISTRIBUTEUR',0,'OPERATEUR',1,3);
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code, ts_rang) values ('AGENCE',0,'DISTRIBUTEUR',4);
insert into types_structures(ts_code,ts_autonome_allowed,ts_parent_code, ts_rang) values ('GUICHET',1,'AGENCE',5);

--Insere um usuario com login admin e senha  = 123mudar
delete from usuario;
INSERT INTO "public".usuario (id, login, senha, salt)
	VALUES (200, 'admin', '8e376f022c75fc5235cb44d0b0b0cb0ca84d268a', '2b3b5d59-218c-4655-95c7-29bbff65e3f7');


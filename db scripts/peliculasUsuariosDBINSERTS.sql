-- Inserts datos iniciales
-- Usuarios
INSERT INTO usuarios (idUsuario, username, password, correo, enable) values 
(1, "admin", "admin", "admin@uah.es",1),
(2, "usuario1", "usuario1", "usuario1@uah.es",1);

-- Criticas
INSERT INTO criticas (idCritica, idPelicula, valoracion, nota, fecha, Usuarios_idUsuario)  values 
(1,1,"Me ha gustado mucho", 8, "2023-12-12",2),
(2,2,"No me ha gustado mucho", 4, "2023-12-12",2);
 
-- Authorities
INSERT INTO authorities (idRol, authority) values
(1, "ROLE_ADMIN"),
(2, "ROLE_USER");

-- Usuarios tiene authorities
INSERT INTO usuarios_tiene_authorities (Usuarios_idUsuario, Authorities_idRol) values
(1,1),
(2,2);


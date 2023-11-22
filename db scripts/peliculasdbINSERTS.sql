#ACTORES
insert into actor (nombre, fecha_nacimiento, pais) values 
('Ryan Gosling', '1980-11-12','Canadá'),
('Ana de Armas', '1988-04-30','Cuba'),
('Harrison Ford', '1942-07-13','Estados Unidos'),
('Jared Leto', '1971-12-26','Estados Unidos'),
('Margot Robbie', '1990-07-02','Australia'),
('Will Ferrel', '1967-07-16','Estados Unidos'),
('Michael Cera', '1988-06-07','Canadá'),
('Emma Stone', '1988-11-06','Estados Unidos'),
('Jonah Hill', '1983-12-20','Estados Unidos'),
('Christopher Mintz-Plasse', '1989-06-20','Estados Unidos'),
('Ben Stiller', '1965-11-30','Estados Unidos'),
('Owen Wilson', '1968-11-18','Estados Unidos'),
('Christian Bale', '1974-01-30','Reino Unido'),
('Willem Dafoe', '1955-07-22','Estados Unidos'),
('Leonardo DiCaprio', '1974-11-11','Estados Unidos'),
('Mads Mikkelsen', '1965-11-22','Dinamarca'),
('Daniel Craig', '1968-03-02','Reino Unido'),
('Chris Evans', '1981-06-13','Estados Unidos'),
('Mary Elizabeth Winstead', '1984-11-28','Estados Unidos');

#PELICULAS
insert into pelicula (titulo, anno, duracion, pais, direccion, genero, sinopsis, portada) values 
('Blade Runner 2049', 2017, 163, 'Estados Unidos', 'Dennis Villeneuve', 'Ciencia ficción','Treinta años después de los eventos del primer film, un nuevo blade runner, K (Ryan Gosling) descubre un secreto profundamente oculto que podría acabar con el caos que impera en la sociedad. El descubrimiento de K le lleva a iniciar la búsqueda de Rick Deckard (Harrison Ford), un blade runner al que se le perdió la pista hace 30 años.','Blade Runner 2049.jpg'),
('Barbie', 2023, 114, 'Estados Unidos', 'Greta Gerwig', 'Comedia, Fantástico','Barbie (Margot Robbie) lleva una vida ideal en Barbieland, allí todo es perfecto, con chupi fiestas llenas de música y color, y todos los días son el mejor día. Claro que Barbie se hace algunas preguntas, cuestiones bastante incómodas que no encajan con el mundo idílico en el que ella y las demás Barbies viven. Cuando Barbie se dé cuenta de que es capaz de apoyar los talones en el suelo, y tener los pies planos, decidirá calzarse unos zapatos sin tacones y viajar hasta el mundo real.','Barbie.jpg'),
('Supersalidos', 2007, 114, 'Estados Unidos', 'Greg Mottola', 'Comedia','En una de sus últimas noches como estudiantes de instituto, Evan (Michael Cera) y Seth (Jonah Hill), dos amigos inadaptados, tropezarán con diversas dificultades para comprar bebidas para una fiesta a la que han invitado a las chicas de sus sueños. En su peripecia les acompañará el indescriptible McLovin (Christopher Mintz-Plasse), otro inadaptado que acaba de comprarse un carnet falso para que le sirva de salvoconducto en la compra de alcohol.','Supersalidos.jpg'),
('Zoolander', 2001, 89, 'Estados Unidos', 'Ben Stiller', 'Comedia','Derek Zoolander (Stiller) ha sido el modelo masculino más cotizado durante los últimos tres años. La noche de la gala que podría suponer su cuarta corona, el galardón se lo lleva un nuevo modelo llamado Hansel (Wilson). Derek queda en entredicho y como un idiota, y decide retirarse. Sin embargo, un prestigioso diseñador le pide que desfile para él.','Zoolander.jpg'),
('American Psycho', 2000, 101, 'Estados Unidos', 'Mary Harron', 'Drama, Terror','En la década de 1980, Patrick Bateman es un exitoso "yuppie" obsesionado por la competencia y por la perfección material, que utiliza los más caros cosméticos masculinos, equipos de gimnasia, solárium y demás maquinaria estética para lograr un cuerpo atlético y bien acicalado, identificador material del éxito social.','American Psycho.jpg'),
('El lobo de Wall Street', 2013, 179, 'Estados Unidos', 'Martin Scorsese', 'Comedia, Drama','La historia del corredor de bolsa neoyorquino Jordan Belfort, quien, con poco más de veinte años, fue apodado "el lobo de Wall Street" por su enorme éxito y fortuna como fundador de la agencia bursátil Stratton Oakmont.','El lobo de wall street.jpg'),
('La La Land', 2016, 127, 'Estados Unidos', 'Damien Chazelle', 'Musical, Romance, Comedia, Drama','Mia (Emma Stone), una joven aspirante a actriz que trabaja como camarera mientras acude a castings, y Sebastian (Ryan Gosling), un pianista de jazz que se gana la vida tocando en sórdidos tugurios, se enamoran, pero su gran ambición por llegar a la cima en sus carreras artísticas amenaza con separarlos.','La La Land.jpg'),
('La caza', 2012, 111, 'Dinamarca', 'Thomas Vinterberg', 'Drama','Tras un divorcio difícil, Lucas, un hombre de cuarenta años, ha encontrado una nueva novia, un nuevo trabajo y trata de reconstruir su relación con Marcus, su hijo adolescente. Pero algo empieza a ir mal: un detalle cualquiera, un comentario inocente y una mentira insignificante que se extiende como un virus invisible sembrando el estupor y la desconfianza en una pequeña población.','La caza.jpg'),
('Casino Royale', 2006, 144, 'Reino Unido', 'Martin Campbell', 'Acción, Thriller','La primera misión del agente británico James Bond como agente 007 lo lleva hasta Le Chiffre, banquero de los terroristas de todo el mundo. Para detenerlo y desmantelar la red de terrorismo, Bond debe derrotarlo en una arriesgada partida de póquer en el Casino Royale.','Casino Royale.jpg'),
('Scott Pilgrim contra el mundo', 2010, 112, 'Estados Unidos', 'Edgar Wright', 'Comedia, Acción, Romance','Scott Pilgrim es un joven que conoce a la mujer de sus sueños, Ramona V. Flowers. Pero para conquistar su corazón, deberá primero luchar y vencer a sus siete malvadas exparejas, misión harto complicada, pues tienen poderes especiales... ','Scott Pilgrim contra el mundo.jpg'),
('Puñales por la espalda', 2019, 130, 'Estados Unidos', 'Rian Johnson', 'Thriller','Un misterio moderno en el que un grupo de investigadores ha de descubrir al responsable del asesinato del patriarca de una excéntrica familia.','Puñales por la espalda.jpg');


#PELICULAS_ACTORES
insert into peliculasactores values 
(1,1),
(1,2),
(1,3),
(1,4),
(2,1),
(2,5),
(2,6),
(2,7),
(3,7),
(3,8),
(3,9),
(3,10),
(4,11),
(4,12),
(4,6),
(5,13),
(5,14),
(6,15),
(6,5),
(6,9),
(7,1),
(7,8),
(8,16),
(9,16),
(9,17),
(10,7),
(10,18),
(10,19),
(11,2),
(11,17),
(11,18);

#Consulta para ver los actores y peliculas que hay relacionados
#select titulo, nombre, pelicula_id, actor_id from peliculasactores join actor on actor.id=actor_id join pelicula on pelicula.id = pelicula_id
#select * from peliculasactores
#select * from pelicula
#select * from actor

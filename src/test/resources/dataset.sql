insert into client values (1, "lorena@email.com", "Lorena", "hola123223", 1, "Calle 3#4", "Lopez", "12312312");
insert into client values (2, "marta@email.com", "Marta", "olo312233", 1, "Cra 3#4", "Wayne", "12313332");
insert into client values (3, "pepe@email.com", "Pepe", "aaa12233", 1, "Cra 69B", "Perez", "12311111");
insert into client values (4, "pedro@email.com", "Pedro", "wwww23123", 1, "Cra 7 cl 2", "Martinez", "23212311");
insert into client values (5, "Andrea@email.com", "Andrea", "2342342", 1, "Cra 5 #7", "Velasquez", "233212");

insert into product values (1, 1, '2023/02/02', "Tabla para picar homebliss", '2023/03/24', "Tabla de picar", 75000, 1, 12, 1);
insert into product values (2, 2, '2023/02/12', "Trituradora con seguridad homebliss", '2023/04/10', "Trituradora", 140000, 1, 7, 2);
insert into product values (3, 3, '2023/03/01', "Colador retractil lo que lo hace muy fácil de guardar", '2023/03/24', "Colador retractil", 30000, 2, 20, 3);
insert into product values (4, 4, '2023/03/10', "Licuadora de 4ltrs homebliss, megalicuadora", '2023/03/24', "Mega licuadora", 300000, 2, 4, 4);
insert into product values (5, 5, '2023/01/14', "Juego de cuchillos en acero inoxidable y diseño minimalista", '2023/03/24', "Juego de cuchillos", 100000, 3, 10, 5);

insert into client_favorites values (1, 1);
insert into client_favorites values (2, 2);
insert into client_favorites values (3, 3);
insert into client_favorites values (4, 4);
insert into client_favorites values (5, 5);

insert into moderator values (1, "moderador1@gmail.com", "Brandon", "moderador1@", 1);
insert into moderator values (2, "moderador2@gmail.com", "Paulina", "moderador2@", 1);
insert into moderator values (3, "moderador3@gmail.com", "Sebastian", "moderador3@", 1);

insert into product_moderator values(1, '2023/02/03', "Ta todo en orden", 2, 1, 1);
insert into product_moderator values(2, '2023/02/13', "No tiene imagenes", 3, 2, 2);
insert into product_moderator values(3, '2023/03/02', "", 1, 3, 3);
insert into product_moderator values(4, '2023/03/11', "Ta todo en orden", 2, 1, 4);
insert into product_moderator values(5, '2023/01/15', "Ta todo en orden", 2, 2, 5);

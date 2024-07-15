create table usuarios(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    clave varchar(300) not null unique,
    email varchar(100) not null unique,


    primary key(id)

);

create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(300) not null unique,
    fecha_creacion varchar(100) not null unique,
    status varchar(100) not null,
    autor_id bigint not null,


    primary key(id),
    constraint fk_topico_usuarioid foreign key(autor_id) references usuarios(id)

);
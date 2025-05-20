CREATE TABLE usuarios(

    id bigint NOT NULL auto_increment,
    nome varchar(100) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    senha varchar(250) NOT NULL,
    role varchar(20) NOT NULL,
    ativo tinyint NOT NULL,

    primary key(id)

);
select * from tabeladepessoas;

ALTER TABLE tabeladepessoas
add COLUMN foto bytea not null;

DROP TABLE tabeladepessoas;

CREATE TABLE tabeladepessoas (
    id serial primary key,
    nome varchar(255) not null,
    foto bytea not null);

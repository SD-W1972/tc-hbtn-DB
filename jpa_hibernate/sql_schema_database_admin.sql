CREATE TABLE pessoas (id  integer, cpf varchar(255), dataDeNascimento date, email varchar(255), idade integer not null, nome varchar(255), primary key (id))
CREATE TABLE produtos (id  integer, nome varchar(255), preco double precision not null, quantidade integer not null, status boolean not null, primary key (id))

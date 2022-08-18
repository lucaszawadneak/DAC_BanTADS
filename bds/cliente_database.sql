CREATE TABLE IF NOT EXISTS endereco (
    id BIGINT PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento varchar(255),
    cep varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    estado varchar(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf varchar(11) NOT NULL,
    endereco BIGINT,
    CONSTRAINT fk_endereco FOREIGN KEY(endereco) REFERENCES endereco(id)
);

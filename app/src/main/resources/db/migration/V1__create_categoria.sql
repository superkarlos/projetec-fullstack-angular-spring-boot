CREATE TABLE categoria (
    codigo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO categoria (nome) VALUES ('carlos');
INSERT INTO categoria (nome) VALUES ('luiz');
INSERT INTO categoria (nome) VALUES ('bruna');

CREATE TABLE pessoa (
    codigo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50) ,
    numero VARCHAR(50) ,
    complemento VARCHAR(50) ,
    bairro VARCHAR(50) ,
    cep VARCHAR(9) ,
    cidade VARCHAR(50) ,
    estado VARCHAR(50) ,
    ativo BOOLEAN NOT NULL
);

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES
('Ana Paula', 'Rua do Sol', '77', 'Bloco B', 'Petrópolis', '59020-390', 'Natal', 'RN', TRUE),
('Rafael Lima', 'Av. Prudente de Morais', '1020', NULL, 'Tirol', '59020-400', 'Natal', 'RN', TRUE),
('Fernanda Rocha', 'Rua Apodi', '15', 'Sala 01', 'Cidade Alta', '59025-000', 'Natal', 'RN', TRUE);

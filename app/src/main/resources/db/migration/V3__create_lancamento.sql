-- =========================
-- TABELA LANCAMENTO
-- =========================
CREATE TABLE lancamento (
    codigo BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descricao VARCHAR(150) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor NUMERIC(15,2) NOT NULL,
    observacao TEXT,

    tipo VARCHAR(20) NOT NULL,

    pessoa_id BIGINT NOT NULL,
    categoria_id BIGINT NOT NULL,

    CONSTRAINT fk_lancamento_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa(codigo),

    CONSTRAINT fk_lancamento_categoria
        FOREIGN KEY (categoria_id) REFERENCES categoria(codigo)
);
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


INSERT INTO lancamento
(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, pessoa_id, categoria_id)
VALUES
('Pagamento de aluguel', '2026-02-10', '2026-02-09', 1200.00, 'Aluguel referente a fevereiro', 'DESPESA', 1, 1);

INSERT INTO lancamento
(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, pessoa_id, categoria_id)
VALUES
('Salário mensal', '2026-02-05', '2026-02-05', 3500.00, 'Salário empresa XPTO', 'RECEITA', 2, 2);

INSERT INTO lancamento
(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, pessoa_id, categoria_id)
VALUES
('Conta de energia', '2026-02-15', NULL, 230.45, 'Cosern - Janeiro', 'DESPESA', 1, 3);

INSERT INTO lancamento
(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, pessoa_id, categoria_id)
VALUES
('Serviço freelance', '2026-02-20', '2026-02-18', 800.00, 'Desenvolvimento de sistema', 'RECEITA', 3, 1);

INSERT INTO lancamento
(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, pessoa_id, categoria_id)
VALUES
('Internet residencial', '2026-02-12', '2026-02-12', 120.99, 'Plano fibra ótica', 'DESPESA', 2, 2);
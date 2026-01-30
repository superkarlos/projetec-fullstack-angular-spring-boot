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
('Pagamento aluguel janeiro', '2026-01-10', '2026-01-10', 1200.00, 'Pago em dia', 'DESPESA', 1, 1),
('Conta de energia', '2026-01-15', '2026-01-14', 230.45, 'Residencial', 'DESPESA', 2, 2),
('Salário mensal', '2026-01-05', '2026-01-05', 3500.00, 'Salário CLT', 'RECEITA', 3, 3),
('Internet residencial', '2026-01-18', '2026-01-18', 120.00, NULL, 'DESPESA', 1, 2),
('Venda de serviço', '2026-01-20', '2026-01-20', 800.00, 'Projeto pontual', 'RECEITA', 2, 1),

('Mensalidade academia', '2026-01-12', '2026-01-12', 95.00, NULL, 'DESPESA', 3, 2),
('Compra supermercado', '2026-01-14', '2026-01-14', 540.30, 'Compra mensal', 'DESPESA', 1, 3),
('Freelance design', '2026-01-22', '2026-01-22', 1200.00, NULL, 'RECEITA', 2, 1),
('Conta de água', '2026-01-16', '2026-01-17', 89.75, NULL, 'DESPESA', 3, 2),
('Manutenção carro', '2026-01-25', '2026-01-26', 780.00, 'Troca de peças', 'DESPESA', 1, 1),

('Venda produto online', '2026-01-28', '2026-01-28', 650.00, NULL, 'RECEITA', 2, 3),
('Plano de saúde', '2026-01-08', '2026-01-08', 420.00, NULL, 'DESPESA', 3, 1),
('Curso online', '2026-01-19', '2026-01-19', 199.90, NULL, 'DESPESA', 1, 2),
('Consultoria', '2026-01-23', '2026-01-23', 1500.00, NULL, 'RECEITA', 2, 2),
('Compra farmácia', '2026-01-11', '2026-01-11', 78.60, NULL, 'DESPESA', 3, 3),

('Bônus anual', '2026-01-30', '2026-01-30', 2500.00, 'Bônus empresa', 'RECEITA', 1, 1),
('Seguro residência', '2026-01-07', '2026-01-07', 110.00, NULL, 'DESPESA', 2, 2),
('Venda equipamento', '2026-01-27', '2026-01-27', 900.00, NULL, 'RECEITA', 3, 3),
('Combustível', '2026-01-13', '2026-01-13', 260.00, NULL, 'DESPESA', 1, 1),
('Pagamento fornecedor', '2026-01-21', '2026-01-21', 1300.00, NULL, 'DESPESA', 2, 2),

('Aula particular', '2026-01-24', '2026-01-24', 300.00, NULL, 'RECEITA', 3, 3),
('Compra material escritório', '2026-01-09', '2026-01-09', 185.40, NULL, 'DESPESA', 1, 2),
('Venda serviço manutenção', '2026-01-26', '2026-01-26', 750.00, NULL, 'RECEITA', 2, 1),
('Conta telefone', '2026-01-17', '2026-01-17', 99.90, NULL, 'DESPESA', 3, 2),
('Licença software', '2026-01-06', '2026-01-06', 299.00, NULL, 'DESPESA', 1, 3),

('Pagamento cliente X', '2026-01-29', '2026-01-29', 1800.00, NULL, 'RECEITA', 2, 2),
('Compra notebook', '2026-01-04', '2026-01-04', 4200.00, 'Equipamento novo', 'DESPESA', 3, 1),
('Venda serviço web', '2026-01-31', '2026-01-31', 2100.00, NULL, 'RECEITA', 1, 2),
('Taxa bancária', '2026-01-03', '2026-01-03', 35.00, NULL, 'DESPESA', 2, 3),
('Pagamento condomínio', '2026-01-10', '2026-01-10', 450.00, NULL, 'DESPESA', 3, 1),

('Rendimento investimento', '2026-01-28', '2026-01-28', 320.00, NULL, 'RECEITA', 1, 3),
('Compra móveis', '2026-01-15', '2026-01-15', 1800.00, NULL, 'DESPESA', 2, 1),
('Serviço fotografia', '2026-01-20', '2026-01-20', 950.00, NULL, 'RECEITA', 3, 2),
('Pagamento imposto', '2026-01-18', '2026-01-18', 670.00, NULL, 'DESPESA', 1, 1),
('Venda consultoria TI', '2026-01-22', '2026-01-22', 3000.00, NULL, 'RECEITA', 2, 3);

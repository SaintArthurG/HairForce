
-- Criação da tabela auxiliar para os serviços
CREATE TABLE agendamento_servicos (
    agendamento_id BIGINT NOT NULL,
    servico VARCHAR(50) NOT NULL,
    CONSTRAINT fk_agendamento_servico
        FOREIGN KEY (agendamento_id)
        REFERENCES agendamentos(id)
        ON DELETE CASCADE
);
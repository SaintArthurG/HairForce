-- Adicionando usuario_id e foreifn key na tabela de agendamentos
ALTER TABLE agendamentos
ADD usuario_id BIGINT NOT NULL,
ADD CONSTRAINT fk_agendamentos_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

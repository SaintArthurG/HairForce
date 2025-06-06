CREATE TABLE agendamentos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ativo TINYINT NOT NULL,
    barbeiro_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    data DATETIME NOT NULL,
    motivo_cancelamento varchar(100),
    FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id) ON UPDATE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON UPDATE CASCADE
);
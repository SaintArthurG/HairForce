CREATE TABLE agendamentos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    hora VARCHAR(10) NOT NULL,
    servico VARCHAR(50) NOT NULL,
    barbeiro_id BIGINT,
    FOREIGN KEY (barbeiro_id) REFERENCES barbeiros(id)
);
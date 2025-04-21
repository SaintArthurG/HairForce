-- Aumentando o tamanho do campo 'senha' de 50 para 250 caracteres
ALTER TABLE usuarios
MODIFY COLUMN senha VARCHAR(250);
CREATE TABLE transferencia (
    id VARCHAR(36) PRIMARY KEY,
    data DATE NOT NULL,
    origem_id BIGINT,
    destino_id BIGINT,
    valor DECIMAL(15, 2) NOT NULL,
    CONSTRAINT fk_origem FOREIGN KEY (origem_id) REFERENCES conta(id),
    CONSTRAINT fk_destino FOREIGN KEY (destino_id) REFERENCES conta(id)
);
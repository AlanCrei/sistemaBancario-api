CREATE TABLE conta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    banco_id BIGINT NOT NULL,
    conta VARCHAR(10) NOT NULL,
    beneficiario VARCHAR(60) NOT NULL,
    saldo DECIMAL(15, 2) DEFAULT 1000.00 NOT NULL,
    CONSTRAINT fk_banco FOREIGN KEY (banco_id) REFERENCES banco(id),
    CONSTRAINT unique_banco_conta UNIQUE (banco_id, conta)
);
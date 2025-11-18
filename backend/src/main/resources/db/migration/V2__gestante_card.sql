ALTER TABLE gestacoes ADD COLUMN risco_nivel VARCHAR(30);
ALTER TABLE gestacoes ADD COLUMN risco_descricao VARCHAR(1000);
ALTER TABLE gestacoes ADD COLUMN unidade_saude VARCHAR(200);
ALTER TABLE gestacoes ADD COLUMN inicio_pre_natal DATE;

CREATE TABLE vacinacoes (
  id BIGSERIAL PRIMARY KEY,
  gestacao_id BIGINT NOT NULL,
  vacina VARCHAR(100) NOT NULL,
  dose VARCHAR(50),
  data DATE,
  observacao VARCHAR(500),
  CONSTRAINT fk_v_gestacao FOREIGN KEY (gestacao_id) REFERENCES gestacoes(id)
);

CREATE TABLE bebes (
  id BIGSERIAL PRIMARY KEY,
  gestacao_id BIGINT UNIQUE,
  nome VARCHAR(150),
  sexo VARCHAR(10),
  data_nascimento DATE,
  peso_nascimento NUMERIC(6,2),
  CONSTRAINT fk_b_gestacao FOREIGN KEY (gestacao_id) REFERENCES gestacoes(id)
);
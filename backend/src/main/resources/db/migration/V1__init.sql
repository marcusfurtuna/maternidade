CREATE TABLE perfis (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  username VARCHAR(80) NOT NULL UNIQUE,
  senha_hash VARCHAR(200) NOT NULL
);

CREATE TABLE usuarios_perfis (
  usuario_id BIGINT NOT NULL,
  perfil_id BIGINT NOT NULL,
  CONSTRAINT fk_up_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  CONSTRAINT fk_up_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id),
  CONSTRAINT pk_up PRIMARY KEY (usuario_id, perfil_id)
);

CREATE TABLE pacientes (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  data_nascimento DATE,
  telefone VARCHAR(20),
  email VARCHAR(120),
  endereco VARCHAR(250),
  tipo_sanguineo VARCHAR(3),
  alergias VARCHAR(500),
  comorbidades VARCHAR(500),
  status_gestacao VARCHAR(30)
);

CREATE TABLE gestacoes (
  id BIGSERIAL PRIMARY KEY,
  paciente_id BIGINT NOT NULL,
  dum DATE,
  idade_gestacional_semanas INTEGER,
  dpp DATE,
  gestacoes_previas INTEGER,
  partos INTEGER,
  abortos INTEGER,
  status VARCHAR(30),
  CONSTRAINT fk_g_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);

CREATE TABLE consultas_prenatal (
  id BIGSERIAL PRIMARY KEY,
  gestacao_id BIGINT NOT NULL,
  data DATE NOT NULL,
  profissional_id BIGINT,
  pa VARCHAR(10),
  peso NUMERIC(6,2),
  observacoes VARCHAR(1000),
  CONSTRAINT fk_c_gestacao FOREIGN KEY (gestacao_id) REFERENCES gestacoes(id)
);

CREATE TABLE exames (
  id BIGSERIAL PRIMARY KEY,
  gestacao_id BIGINT NOT NULL,
  tipo VARCHAR(100) NOT NULL,
  data DATE NOT NULL,
  resultado_resumo VARCHAR(1000),
  CONSTRAINT fk_e_gestacao FOREIGN KEY (gestacao_id) REFERENCES gestacoes(id)
);
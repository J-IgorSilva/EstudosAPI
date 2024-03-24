CREATE TABLE IF NOT EXISTS public.hospital (
    id SERIAL PRIMARY KEY,
    capacidade_atendimento INTEGER,
    data_de_inauguracao DATE,
    endereco VARCHAR(255),
    nome VARCHAR(255),
    numero_funcionarios INTEGER,
    numero_leitos INTEGER
);

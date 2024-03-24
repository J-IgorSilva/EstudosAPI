CREATE TABLE IF NOT EXISTS public.pessoa (
    id SERIAL PRIMARY KEY,
    alta_paciente DATE,
    atendimento_paciente DATE,
    cargo VARCHAR(255),
    data_nascimento DATE,
    doenca VARCHAR(255),
    idade INTEGER,
    licenca VARCHAR(255),
    nome VARCHAR(255),
    nome_hospital VARCHAR(255),
    salario INTEGER,
    hospital_id INTEGER REFERENCES public.hospital(id)
);

ALTER TABLE IF EXISTS public.pessoa
ADD CONSTRAINT fk_hospital_pessoa FOREIGN KEY (hospital_id) REFERENCES public.hospital(id);

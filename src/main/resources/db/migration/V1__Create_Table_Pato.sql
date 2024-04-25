CREATE TABLE IF NOT EXISTS public.pato
(
    id uuid NOT NULL,
    fk_mae_id uuid NULL,
    nome character varying NOT NULL,
    valor double precision NOT NULL,
    vendido boolean NOT NULL,
    PRIMARY KEY (id)
);

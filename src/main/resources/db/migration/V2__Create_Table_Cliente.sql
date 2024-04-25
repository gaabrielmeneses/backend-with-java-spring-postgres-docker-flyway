CREATE TABLE IF NOT EXISTS public.cliente
(
    id uuid NOT NULL,
    nome character varying NOT NULL,
    desconto boolean DEFAULT false,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.venda
(
    id uuid NOT NULL,
    fk_cliente_id uuid NOT NULL,
    fk_pato_id uuid NOT NULL,
    valor double precision NOT NULL,
    PRIMARY KEY (id)
);

BEGIN;

CREATE TABLE IF NOT EXISTS public.pato
(
    id uuid NOT NULL,
    nome character varying NOT NULL,
    valor double precision DEFAULT 70.00,
    vendido boolean DEFAULT false,
    mae boolean DEFAULT false,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.pato_mae
(
    id uuid NOT NULL,
    fk_pato_filho uuid NOT NULL,
    fk_pato_mae uuid NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.cliente
(
    id uuid NOT NULL,
    nome character varying NOT NULL,
    desconto boolean DEFAULT false,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.venda_pato
(
    id uuid NOT NULL,
    fk_cliente_id uuid NOT NULL,
    fk_pato_id uuid NOT NULL,
    valor_venda double precision NOT NULL
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.pato_mae
    ADD CONSTRAINT fk_pato_mae FOREIGN KEY (fk_pato_mae)
    REFERENCES public.pato (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.pato_mae
    ADD CONSTRAINT fk_pato_filho FOREIGN KEY (fk_pato_filho)
    REFERENCES public.pato (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.venda_pato
    ADD CONSTRAINT fk_cliente_id FOREIGN KEY (fk_cliente_id)
    REFERENCES public.cliente (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.venda_pato
    ADD CONSTRAINT fk_pato_id FOREIGN KEY (fk_pato_id)
    REFERENCES public.pato (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;
CREATE DATABASE hotel
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE public.rooms
(
    room_id serial NOT NULL,
    type text NOT NULL,
    price double precision NOT NULL,
    available boolean NOT NULL DEFAULT true,
    PRIMARY KEY (room_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public.clients
(
    client_id serial NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    PRIMARY KEY (client_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public.booking
(
    booking_id integer NOT NULL DEFAULT nextval('booking_booking_id_seq'::regclass),
    client_id integer NOT NULL DEFAULT nextval('booking_client_id_seq'::regclass),
    room_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (booking_id),
    CONSTRAINT "client_id_to_client_Table" FOREIGN KEY (client_id)
        REFERENCES public.clients (client_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT room_id_to_room_table FOREIGN KEY (room_id)
        REFERENCES public.rooms (room_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE INDEX "fki_client_id_to_client_Table"
    ON public.booking USING btree
    (client_id)
    TABLESPACE pg_default;

CREATE INDEX fki_room_id_to_room_table
    ON public.booking USING btree
    (room_id)
    TABLESPACE pg_default;
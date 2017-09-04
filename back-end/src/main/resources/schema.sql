CREATE TABLE IF NOT EXISTS public.rooms
(
  room_id   SERIAL           NOT NULL,
  type      TEXT             NOT NULL,
  price     DOUBLE PRECISION NOT NULL,
  available BOOLEAN          NOT NULL DEFAULT TRUE,
  PRIMARY KEY (room_id)
);

CREATE TABLE IF NOT EXISTS public.clients
(
  client_id   SERIAL      NOT NULL,
  first_name  VARCHAR(45) NOT NULL,
  last_name   VARCHAR(45) NOT NULL,
  start_date  VARCHAR(45) NOT NULL,
  end_date    VARCHAR(45) NOT NULL,
  room_number INT,
  PRIMARY KEY (client_id),
  FOREIGN KEY (room_number) REFERENCES public.rooms (room_id)
);
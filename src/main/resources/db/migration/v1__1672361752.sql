CREATE TABLE IF NOT EXISTS t_car (
  id UUID NOT NULL DEFAULT uuid_generate_v1() ,
  manufacturer_id UUID NULL,
  model VARCHAR(255) NOT NULL.
  primary key (id),
  CONSTRAINT fk_t_manufacturer FOREIGN KEY(manufacturer_id) REFERENCES t_manufacturer(id)
)

CREATE TABLE IF NOT EXISTS t_manufacturer(
    id UUID NOT NULL DEFAULT uuid_generate_v1(),
    name VARCHAR(255) NOT NULL,
    primary key(id)
)
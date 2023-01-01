
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS t_car (
  id UUID NOT NULL DEFAULT uuid_generate_v1(),
  manufacturer VARCHAR(255) NULL,
  model VARCHAR(255) NOT NULL,
  "year" VARCHAR(50) NULL,
  image_url VARCHAR(255) NULL,
  hash_code VARCHAR(255) NOT NULL,
  created_at DATE NOT NULL DEFAULT CURRENT_DATE,
  primary key (id)
);

CREATE UNIQUE INDEX u_man_model_year ON t_car(hash_code);



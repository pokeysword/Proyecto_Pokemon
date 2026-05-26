CREATE DATABASE proyecto_pokemon;

\c proyecto_pokemon

CREATE SCHEMA IF NOT EXISTS pokemon;
SET search_path TO pokemon;

CREATE TABLE tipo (
  code text PRIMARY KEY
);

CREATE TABLE habilidad (
  code text PRIMARY KEY,
  nombre text NOT NULL
);

CREATE TABLE movimiento (
  code text PRIMARY KEY,
  nombre text NOT NULL
);

CREATE TABLE pokemon (
  id integer PRIMARY KEY,
  nombre text NOT NULL UNIQUE,
  nivel integer NOT NULL CHECK (nivel > 0),
  ps integer NOT NULL CHECK (ps > 0),
  ataque integer NOT NULL CHECK (ataque > 0),
  defensa integer NOT NULL CHECK (defensa > 0),
  ataque_especial integer NOT NULL CHECK (ataque_especial > 0),
  defensa_especial integer NOT NULL CHECK (defensa_especial > 0),
  velocidad integer NOT NULL CHECK (velocidad > 0),
  habilidad_code text NOT NULL REFERENCES habilidad(code)
);

CREATE TABLE pokemon_tipo (
  pokemon_id integer NOT NULL REFERENCES pokemon(id) ON DELETE CASCADE,
  tipo_code text NOT NULL REFERENCES tipo(code),
  slot smallint NOT NULL CHECK (slot BETWEEN 1 AND 2),
  PRIMARY KEY (pokemon_id, slot),
  UNIQUE (pokemon_id, tipo_code)
);

CREATE TABLE pokemon_movimiento (
  pokemon_id integer NOT NULL REFERENCES pokemon(id) ON DELETE CASCADE,
  movimiento_code text NOT NULL REFERENCES movimiento(code),
  slot smallint NOT NULL CHECK (slot BETWEEN 1 AND 4),
  PRIMARY KEY (pokemon_id, slot),
  UNIQUE (pokemon_id, movimiento_code)
);

CREATE INDEX pokemon_tipo_tipo_idx ON pokemon_tipo (tipo_code);
CREATE INDEX pokemon_movimiento_movimiento_idx ON pokemon_movimiento (movimiento_code);

INSERT INTO tipo (code) VALUES
  ('NORMAL'),
  ('FUEGO'),
  ('AGUA'),
  ('PLANTA'),
  ('ELECTRICO'),
  ('HIELO'),
  ('LUCHA'),
  ('VENENO'),
  ('TIERRA'),
  ('VOLADOR'),
  ('PSIQUICO'),
  ('BICHO'),
  ('ROCA'),
  ('FANTASMA'),
  ('DRAGON'),
  ('SINIESTRO'),
  ('ACERO'),
  ('HADA');

INSERT INTO habilidad (code, nombre) VALUES
  ('Levitate', 'Levitate'),
  ('RoughSkin', 'RoughSkin'),
  ('SereneGrace', 'SereneGrace'),
  ('ClearBody', 'ClearBody'),
  ('Competitive', 'Competitive'),
  ('Intimidacion', 'Intimidacion'),
  ('Regenerator', 'Regenerator'),
  ('MoldBreaker', 'MoldBreaker'),
  ('Pixilate', 'Pixilate');

INSERT INTO movimiento (code, nombre) VALUES
  ('HydroPump', 'HydroPump'),
  ('VoltSwitch', 'VoltSwitch'),
  ('WillOWisp', 'WillOWisp'),
  ('Protect', 'Protect'),
  ('Earthquake', 'Earthquake'),
  ('DragonClaw', 'DragonClaw'),
  ('RockSlide', 'RockSlide'),
  ('AirSlash', 'AirSlash'),
  ('DazzlingGleam', 'DazzlingGleam'),
  ('CalmMind', 'CalmMind'),
  ('MeteorMash', 'MeteorMash'),
  ('ZenHeadbutt', 'ZenHeadbutt'),
  ('Scald', 'Scald'),
  ('IceBeam', 'IceBeam'),
  ('Recover', 'Recover'),
  ('FlareBlitz', 'FlareBlitz'),
  ('Snarl', 'Snarl'),
  ('Spore', 'Spore'),
  ('EnergyBall', 'EnergyBall'),
  ('RagePowder', 'RagePowder'),
  ('DragonPulse', 'DragonPulse'),
  ('ShadowBall', 'ShadowBall'),
  ('UTurn', 'UTurn'),
  ('IronHead', 'IronHead'),
  ('SwordDance', 'SwordDance'),
  ('HyperVoice', 'HyperVoice'),
  ('MysticalFire', 'MysticalFire');

INSERT INTO pokemon (id, nombre, nivel, ps, ataque, defensa, ataque_especial, defensa_especial, velocidad, habilidad_code) VALUES
  (1, 'RotomWash', 50, 157, 85, 128, 125, 128, 106, 'Levitate'),
  (2, 'Garchomp', 50, 183, 182, 115, 95, 105, 154, 'RoughSkin'),
  (3, 'Togekiss', 50, 191, 90, 115, 140, 135, 113, 'SereneGrace'),
  (4, 'Metagross', 50, 187, 178, 150, 105, 110, 110, 'ClearBody'),
  (5, 'Milotic', 50, 202, 90, 125, 135, 145, 101, 'Competitive'),
  (6, 'Arcanine', 50, 181, 181, 120, 100, 85, 118, 'Intimidacion'),
  (7, 'Amoonguss', 50, 185, 85, 110, 101, 127, 51, 'Regenerator'),
  (8, 'Dragapult', 50, 193, 120, 75, 100, 75, 142, 'ClearBody'),
  (9, 'Excadrill', 50, 185, 185, 115, 85, 105, 154, 'MoldBreaker'),
  (10, 'Sylveon', 50, 185, 185, 115, 85, 105, 154, 'Pixilate');

INSERT INTO pokemon_tipo (pokemon_id, tipo_code, slot) VALUES
  (1, 'ELECTRICO', 1),
  (1, 'AGUA', 2),
  (2, 'DRAGON', 1),
  (2, 'TIERRA', 2),
  (3, 'HADA', 1),
  (3, 'VOLADOR', 2),
  (4, 'ACERO', 1),
  (4, 'PSIQUICO', 2),
  (5, 'AGUA', 1),
  (6, 'FUEGO', 1),
  (7, 'BICHO', 1),
  (7, 'PLANTA', 2),
  (8, 'DRAGON', 1),
  (8, 'FANTASMA', 2),
  (9, 'TIERRA', 1),
  (9, 'ACERO', 2),
  (10, 'HADA', 1);

INSERT INTO pokemon_movimiento (pokemon_id, movimiento_code, slot) VALUES
  (1, 'HydroPump', 1),
  (1, 'VoltSwitch', 2),
  (1, 'WillOWisp', 3),
  (1, 'Protect', 4),
  (2, 'Earthquake', 1),
  (2, 'DragonClaw', 2),
  (2, 'RockSlide', 3),
  (2, 'Protect', 4),
  (3, 'AirSlash', 1),
  (3, 'DazzlingGleam', 2),
  (3, 'CalmMind', 3),
  (3, 'Protect', 4),
  (4, 'MeteorMash', 1),
  (4, 'ZenHeadbutt', 2),
  (4, 'Earthquake', 3),
  (4, 'Protect', 4),
  (5, 'Scald', 1),
  (5, 'IceBeam', 2),
  (5, 'Recover', 3),
  (5, 'Protect', 4),
  (6, 'FlareBlitz', 1),
  (6, 'Snarl', 2),
  (6, 'Protect', 3),
  (7, 'Spore', 1),
  (7, 'EnergyBall', 2),
  (7, 'RagePowder', 3),
  (7, 'Protect', 4),
  (8, 'DragonPulse', 1),
  (8, 'ShadowBall', 2),
  (8, 'UTurn', 3),
  (8, 'Protect', 4),
  (9, 'IronHead', 1),
  (9, 'Earthquake', 2),
  (9, 'SwordDance', 3),
  (9, 'Protect', 4),
  (10, 'HyperVoice', 1),
  (10, 'MysticalFire', 2),
  (10, 'CalmMind', 3),
  (10, 'Protect', 4);

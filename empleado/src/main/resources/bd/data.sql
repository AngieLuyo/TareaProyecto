INSERT INTO profesion (profesion_id, profesion_nombre) VALUES (1,'Ingeniero de Sistemas') ON CONFLICT (profesion_id) DO NOTHING;
INSERT INTO profesion (profesion_id, profesion_nombre) VALUES (2,'Ingeniero de Software') ON CONFLICT (profesion_id) DO NOTHING;
INSERT INTO profesion (profesion_id, profesion_nombre) VALUES (3,'Cientifico de Datos') ON CONFLICT (profesion_id) DO NOTHING;
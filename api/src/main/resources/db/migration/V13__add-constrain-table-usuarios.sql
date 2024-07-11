ALTER TABLE usuarios
ADD CONSTRAINT fk_usuarios_perfil_id
FOREIGN KEY (perfil_id) REFERENCES perfiles(id);
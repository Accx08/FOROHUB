create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(500) not null,
    fecha_creacion datetime not null,
    status varchar(50) not null,
    usuario_id bigint not null,
    curso_id bigint not null,
    respuestas bigint DEFAULT 0,


    primary key(id),

        CONSTRAINT fk_topicos_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
        CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id)
    );
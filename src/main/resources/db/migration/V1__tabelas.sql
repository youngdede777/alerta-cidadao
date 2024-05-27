CREATE SEQUENCE SEQ_USUARIOS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_USUARIOS (
    USUARIO_ID INTEGER DEFAULT SEQ_USUARIOS.NEXTVAL NOT NULL,
    NOME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) UNIQUE NOT NULL,
    SENHA VARCHAR(60) NOT NULL,
    ROLE VARCHAR(50) DEFAULT 'USER'
);

-- Criando as sequências
CREATE SEQUENCE t_crime_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE t_emergencia_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE t_recurso_plc_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE t_suspeito_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE t_localizacao (
    cep    VARCHAR2(11) NOT NULL,
    rua    VARCHAR2(50),
    digito NUMBER(4)
);

ALTER TABLE t_localizacao ADD CONSTRAINT t_localizacao_pk PRIMARY KEY ( cep );

CREATE TABLE t_crime (
    crime_id          NUMBER(5) DEFAULT t_crime_seq.NEXTVAL NOT NULL,
    tp_crime          NUMBER(1) NOT NULL,
    data              DATE NOT NULL,
    status_ocorrencia VARCHAR2(1) NOT NULL,
    t_localizacao_cep VARCHAR2(11)
);


ALTER TABLE t_crime ADD CONSTRAINT t_crime_pk PRIMARY KEY ( crime_id );

CREATE TABLE t_emergencia (
    id_emergencia     NUMBER(5) DEFAULT t_emergencia_seq.NEXTVAL NOT NULL,
    tp_emergencia     NUMBER(1) NOT NULL,
    status            VARCHAR2(1) NOT NULL,
    dt_inicio         DATE,
    dt_fim            DATE,
    t_localizacao_cep VARCHAR2(11)
);

ALTER TABLE t_emergencia ADD CONSTRAINT t_emergencia_pk PRIMARY KEY ( id_emergencia );

CREATE TABLE t_recurso_plc (
    id_recurso        NUMBER(5) DEFAULT t_recurso_plc_seq.NEXTVAL NOT NULL,
    tp_recurso        NUMBER(1) NOT NULL,
    total             NUMBER(5) NOT NULL,
    disponivel        NUMBER(5) NOT NULL,
    t_localizacao_cep VARCHAR2(11)
);

CREATE UNIQUE INDEX t_recurso_policial__idx ON
    t_recurso_plc (
        t_localizacao_cep
    ASC );

ALTER TABLE t_recurso_plc ADD CONSTRAINT t_recurso_policial_pk PRIMARY KEY ( id_recurso );

CREATE TABLE t_suspeito (
    id_suspeito       NUMBER(5) DEFAULT t_suspeito_seq.NEXTVAL NOT NULL,
    nome              VARCHAR2(50) NOT NULL,
    genero            VARCHAR2(1),
    dt_nasc           DATE,
    t_localizacao_cep VARCHAR2(11),
    crime_id          NUMBER(5)  -- Remover essa coluna daqui, pois será uma chave estrangeira
);


ALTER TABLE t_suspeito ADD CONSTRAINT t_suspeito_pk PRIMARY KEY ( id_suspeito );

-- Adicionando as constraints de chave estrangeira
ALTER TABLE t_crime
    ADD CONSTRAINT t_crime_t_localizacao_fk FOREIGN KEY ( t_localizacao_cep )
        REFERENCES t_localizacao ( cep );

ALTER TABLE t_emergencia
    ADD CONSTRAINT t_emergencia_t_localizacao_fk FOREIGN KEY ( t_localizacao_cep )
        REFERENCES t_localizacao ( cep );

ALTER TABLE t_recurso_plc
    ADD CONSTRAINT t_recursoplcl_t_localizacao_fk FOREIGN KEY ( t_localizacao_cep )
        REFERENCES t_localizacao ( cep );

ALTER TABLE t_suspeito
    ADD CONSTRAINT t_suspeito_t_localizacao_fk FOREIGN KEY ( t_localizacao_cep )
        REFERENCES t_localizacao ( cep );

-- Adicionando a coluna crime_id na tabela t_suspeito
ALTER TABLE t_suspeito ADD ( crime_id NUMBER(5) );

-- Criando a constraint de chave estrangeira
ALTER TABLE t_suspeito
    ADD CONSTRAINT t_suspeito_t_crime_fk FOREIGN KEY ( crime_id )
        REFERENCES t_crime ( crime_id );

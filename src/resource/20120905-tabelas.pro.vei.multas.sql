USE TEST;
DROP TABLE IF EXISTS multa;
DROP TABLE IF EXISTS veiculo;
DROP TABLE IF EXISTS proprietario;

CREATE TABLE proprietario (
    codigo         INT(05) PRIMARY KEY,
    nome           VARCHAR(40) NOT NULL,
    end_id         INT(05),
    numero         INT(05),
    telefone       VARCHAR(10),
    email          VARCHAR(30)
);

CREATE TABLE veiculo (
    codigo         INT(05) PRIMARY KEY,
    descr          VARCHAR(40) NOT NULL,
    chassi         DOUBLE,
    codpro         INT(05),
    UNIQUE (descr)
);

CREATE TABLE multa (
  codpro           INT(05),
  codvei           INT(05),
  data             DATE,
  pontuacao        INT(05),
  tipo             ENUM('LEVE','LEVISSIMA','GRAVE','GRAVISSIMA','MEDIA'),
  FOREIGN KEY (codpro) REFERENCES proprietario(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (codvei) REFERENCES veiculo     (codigo) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE ACESSORIO
(
	cod_acessorio        SMALLINT NOT NULL ,
	nome_acessorio       VARCHAR(20) NULL ,
	preco_acessorio      FLOAT NULL 
);

CREATE UNIQUE INDEX XPKACESSORIO ON ACESSORIO
(cod_acessorio   ASC);

CREATE TABLE AGENDAMENTO
(
	cod_cliente          SMALLINT NOT NULL ,
	cod_luthier          SMALLINT NOT NULL ,
	cod_servico          SMALLINT NOT NULL ,
	preco_agen           FLOAT NULL ,
	data_agen            DATE NULL ,
	hora_agen            DATE NULL ,
	observacoes          VARCHAR(100) NULL ,
	cod_agen             SMALLINT NOT NULL 
);

CREATE UNIQUE INDEX XPKAGENDAMENTO ON AGENDAMENTO
(cod_agen   ASC);

CREATE TABLE CLIENTE
(
	nome_cliente         VARCHAR(30) NULL ,
	rg_cliente           CHAR(9)  NOT NULL ,
	endereco_cliente     VARCHAR(50) NULL ,
	bairro_cliente       VARCHAR(20) NULL ,
	cidade_cliente       VARCHAR(20) NULL ,
	uf_cliente           CHAR(2) NULL ,
	telefone_cliente     CHAR(9) NULL ,
	email_cliente        VARCHAR(30) NULL ,
	cod_cliente          SMALLINT NOT NULL
);

CREATE UNIQUE INDEX XPKCLIENTE ON CLIENTE
(cod_cliente   ASC);

ALTER TABLE CLIENTE
  ADD CONSTRAINT PKCLIENTE PRIMARY KEY (cod_cliente);
  
ALTER TABLE CLIENTE MODIFY COLUMN cod_cliente SMALLINT AUTO_INCREMENT;
ALTER TABLE CLIENTE MODIFY COLUMN rg_cliente CHAR(9) not null;
ALTER TABLE CLIENTE MODIFY COLUMN endereco_cliente  VARCHAR(50) not null;



CREATE TABLE FABRICANTE
(
	cod_fabricante       SMALLINT NOT NULL ,
	nome_fabricante      VARCHAR(20) NULL ,
	telefone_fabricante  CHAR(9) NULL ,
	nome_contato         VARCHAR(30) NULL 
);

CREATE UNIQUE INDEX XPKFABRICANTE ON FABRICANTE
(cod_fabricante   ASC);

CREATE TABLE INSTRUMENTO
(
	cod_instrumento      SMALLINT NOT NULL ,
	cod_fabricante       SMALLINT NOT NULL ,
	nome_instrumento     VARCHAR(20) NULL ,
	tipo_instrumento     VARCHAR(20) NULL ,
	preco_instrumento    FLOAT NULL ,
	especificacao        VARCHAR(100) NULL 
);

CREATE UNIQUE INDEX XPKINSTRUMENTO ON INSTRUMENTO
(cod_instrumento   ASC);

CREATE TABLE ITEM
(
	cod_venda            SMALLINT NOT NULL ,
	cod_midia            SMALLINT NOT NULL ,
	cod_acessorio        SMALLINT NOT NULL ,
	cod_instrumento      SMALLINT NOT NULL ,
	quantidade           SMALLINT NULL ,
	cod_livro            SMALLINT NOT NULL ,
	cod_item             SMALLINT NOT NULL 
);

CREATE UNIQUE INDEX XPKITEM ON ITEM
(cod_item   ASC,cod_venda   ASC);

CREATE TABLE LIVRO
(
	cod_livro            SMALLINT NOT NULL ,
	titulo_livro         VARCHAR(30) NULL ,
	autor_livro          VARCHAR(30) NULL ,
	editora_livro        VARCHAR(20) NULL ,
	preco_livro          FLOAT NULL ,
	ano_livro            SMALLINT NULL 
);

CREATE UNIQUE INDEX XPKLIVRO ON LIVRO
(cod_livro   ASC);

CREATE TABLE LUTHIER
(
	cod_luthier          SMALLINT NOT NULL ,
	nome_luthier         VARCHAR(30) NULL ,
	cpf_luthier          CHAR(11) NULL ,
	endereco_luthier     VARCHAR(50) NULL ,
	bairro_luthier       VARCHAR(20) NULL ,
	cidade_luthier       VARCHAR(20) NULL ,
	uf_luthier           CHAR(2) NULL ,
	telefone_luthier     CHAR(9) NULL ,
	email_luthier        VARCHAR(30) NULL ,
	especialidade_luthier VARCHAR(20) NULL 
);

CREATE UNIQUE INDEX XPKLUTHIER ON LUTHIER
(cod_luthier   ASC);

CREATE TABLE MIDIA
(
	cod_midia            SMALLINT NOT NULL ,
	titulo_midia         VARCHAR(30) NULL ,
	autor_midia          VARCHAR(30) NULL ,
	dist_midia           VARCHAR(20) NULL ,
	tipo_midia           VARCHAR(20) NULL ,
	preco_midia          FLOAT NULL ,
	ano_midia            SMALLINT NULL 
);

CREATE UNIQUE INDEX XPKMIDIA ON MIDIA
(cod_midia   ASC);

CREATE TABLE SERVICO
(
	tipo_servico         VARCHAR(20) NULL ,
	preco_servico        FLOAT NULL ,
	desc_servico         VARCHAR(100) NULL ,
	cod_servico          SMALLINT NOT NULL 
);

CREATE UNIQUE INDEX XPKSERVICO ON SERVICO
(cod_servico   ASC);

CREATE TABLE VENDA
(
	cod_venda            SMALLINT NOT NULL ,
	cod_cliente          SMALLINT NOT NULL ,
	total_venda          FLOAT NULL ,
	data_venda           DATE NULL 
);

CREATE UNIQUE INDEX XPKVENDA ON VENDA
(cod_venda   ASC);

  
ALTER TABLE SERVICO
  ADD CONSTRAINT PKSERVICO PRIMARY KEY (cod_servico);
  
ALTER TABLE LUTHIER
  ADD CONSTRAINT PKLUTHIER PRIMARY KEY (cod_luthier);
  
ALTER TABLE AGENDAMENTO
  ADD CONSTRAINT PKAGENDAMENTO PRIMARY KEY (cod_agen);

ALTER TABLE VENDA
  ADD CONSTRAINT PKVENDA PRIMARY KEY (cod_venda);
  
ALTER TABLE ITEM
  ADD CONSTRAINT PK_ITEM PRIMARY KEY (cod_item, cod_venda);
  
ALTER TABLE LIVRO
  ADD CONSTRAINT PK_LIVRO PRIMARY KEY (cod_livro);

ALTER TABLE MIDIA
  ADD CONSTRAINT PK_MIDIA PRIMARY KEY (cod_midia);

ALTER TABLE ACESSORIO
  ADD CONSTRAINT PK_ACESSORIO PRIMARY KEY (cod_acessorio);
  
ALTER TABLE FABRICANTE
  ADD CONSTRAINT PK_FABRICANTE PRIMARY KEY (cod_fabricante);
  
ALTER TABLE INSTRUMENTO
  ADD CONSTRAINT PK_INSTRUMENTO PRIMARY KEY (cod_instrumento);
  
ALTER TABLE AGENDAMENTO
  ADD CONSTRAINT FK_AGENDAMENTO_CLIENTE FOREIGN KEY (cod_cliente) REFERENCES CLIENTE(cod_cliente);
  
ALTER TABLE AGENDAMENTO
  ADD CONSTRAINT FK_AGENDAMENTO_LUTHIER FOREIGN KEY (cod_luthier) REFERENCES LUTHIER(cod_luthier);
  
ALTER TABLE AGENDAMENTO
  ADD CONSTRAINT FK_AGENDAMENTO_SERVICO FOREIGN KEY (cod_cliente) REFERENCES SERVICO(cod_servico);
  
ALTER TABLE VENDA
  ADD CONSTRAINT FK_VENDA_CLIENTE FOREIGN KEY (cod_cliente) REFERENCES CLIENTE(cod_cliente);
  
ALTER TABLE ITEM
  ADD CONSTRAINT FK_ITEM_VENDA FOREIGN KEY (cod_venda) REFERENCES VENDA(cod_venda);
  
ALTER TABLE INSTRUMENTO
  ADD CONSTRAINT FK_INSTRUMENTO_FABRICANTE FOREIGN KEY (cod_fabricante) REFERENCES FABRICANTE(cod_fabricante);
  
create table SIS_USER(
  id_user smallint primary key not null auto_increment,
  nome  varchar(20),
  senha varchar(8)
);

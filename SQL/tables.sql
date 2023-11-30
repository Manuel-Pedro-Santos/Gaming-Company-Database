CREATE TABLE JOGO (
    idalfa VARCHAR(10)PRIMARY KEY,
	nome VARCHAR(50) UNIQUE NOT NULL,
    url TEXT
);

CREATE TABLE REGIAO (
    nome VARCHAR(100) PRIMARY KEY
);


CREATE TABLE PARTIDAS (
    n_sequencial SMALLSERIAL PRIMARY KEY,
    dtinicio DATE,
    dtfinal DATE,
    horainicio TIME,
    horafim TIME,
    regiao_nome VARCHAR(30) REFERENCES REGIAO(nome)
);


CREATE TYPE joga_states AS ENUM('ATIVO','INATIVO','BANIDO');
CREATE TABLE JOGADORES (
    id SMALLSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    estado joga_states,
    partida_numseq SMALLSERIAL REFERENCES PARTIDAS(n_sequencial),
    regiao_nome varchar(30) NOT NULL REFERENCES REGIAO(nome)
);

CREATE TABLE CONVERSA (
    id SMALLSERIAL  PRIMARY KEY,
    chat_id SMallSERIAl NOT NULL,
    jogador_id INTEGER NOT NULL,
    nome VARCHAR(20) NOT NULL,
    FOREIGN KEY (jogador_id) REFERENCES JOGADORES(id)
);


CREATE TYPE multi_states AS ENUM('POR INICIAR','A AGUARDAR JOGADORES','EM CURSO','TERMINADA');
CREATE TABLE Partida_MULTIJOGADOR (
    n_sequencial SMALLSERIAL  NOT NULL REFERENCES PARTIDAS(n_sequencial),
    regiao_nome VARCHAR(30) NOT NULL REFERENCES REGIAO(nome),
    estado multi_states
);

CREATE TABLE Pontuacao_MULTIJOGADOR (
    n_sequencial SMALLSERIAL  NOT NULL,
    regiao_nome VARCHAR(30) NOT NULL,
	id_jogador INTEGER Primary key  NOT NULL,
    pontos INTEGER NOT NULL,
	n_partidas INTEGER NOT NULL,
    FOREIGN KEY (n_sequencial)REFERENCES Partidas(n_sequencial),
	FOREIGN KEY (regiao_nome) REFERENCES Regiao (nome),
    FOREIGN KEY (id_jogador) REFERENCES JOGADORES(id)
);


CREATE TABLE Partida_NORMAL (
    n_sequencial SMALLSERIAL PRIMARY KEY,
    regiao_nome VARCHAR(30) NOT NULL REFERENCES REGIAO(nome),
    Grau INTEGER NOT NULL CHECK (Grau >= 0 AND Grau <= 5),
    id_jogador INTEGER NOT NULL REFERENCES JOGADORES(id),
    pontos INTEGER NOT NULL,
	n_partidas INTEGER NOT NULL
);

CREATE TABLE MENSAGEM (
    id SMALLSERIAL PRIMARY KEY,
    conversa_id SMALLSERIAL NOT NULL REFERENCES CONVERSA(id),
    ordem SMALLSERIAL,
    texto TEXT,
    horaenvio TIMESTAMP,
	jogador_id INTEGER  NOT NULL,
	jogador_username VARCHAR(30) NOT NULL,
	jogador_email VARCHAR(30) NOT NULL,
    FOREIGN KEY (jogador_id) REFERENCES JOGADORES(id),
    FOREIGN KEY (jogador_username) REFERENCES JOGADORES(username),
    FOREIGN KEY(jogador_email) REFERENCES JOGADORES(email)
);

CREATE TABLE CRACHA (
	cracha_id SMALLSERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    limitepontos INTEGER,
    jogo_id VARCHAR(50) REFERENCES JOGO(idalfa)
);

CREATE TABLE JOGADOR_CRACHA (
    cracha_id SMALLSERIAL PRIMARY KEY REFERENCES CRACHA(cracha_id) ,
	jogo_nome varchar(10) UNIQUE NOT NULL REFERENCES JOGO(idalfa),
	cracha_nome VARCHAR(30) REFERENCES CRACHA(nome),
    jogador_id INTEGER NOT NULL REFERENCES JOGADORES(id),
    jogador_username VARCHAR(30) NOT NULL REFERENCES JOGADORES(username),
    jogador_email VARCHAR(30) NOT NULL REFERENCES JOGADORES(email)
);

CREATE TABLE COMPRA (
    jogador_id INTEGER PRIMARY KEY  REFERENCES JOGADORES(id ) ,
    jogador_username VARCHAR(30)UNIQUE NOT NULL REFERENCES JOGADORES(username),
    jogador_email VARCHAR(30)UNIQUE NOT NULL REFERENCES JOGADORES(email),
    jogo_id VARCHAR(50)UNIQUE NOT NULL REFERENCES JOGO(idalfa),
    data DATE,
    preço DECIMAL(10,2)
);

CREATE TABLE JOGADOR_STATS (
    jogador_id INTEGER PRIMARY KEY ,
	FOREIGN KEY (jogador_id) REFERENCES JOGADORES(id),
    jogador_username VARCHAR(30) NOT NULL REFERENCES JOGADORES(username),
    jogador_email VARCHAR(30) NOT NULL REFERENCES JOGADORES(email),
    jogo_id VARCHAR(50) NOT NULL REFERENCES JOGO(idalfa),--
    partidasjogadas INTEGER,
    pontostotais INTEGER,
    totaldeJogosUnicos INTEGER
);

CREATE TABLE JOGO_STATS (
    jogo_id VARCHAR(50) PRIMARY KEY REFERENCES JOGO(idalfa),
    totalpartidas INTEGER,
    pontostotais INTEGER,
    totaldejogadores INTEGER
);

CREATE TABLE AMIZADE (
    jogador_id INTEGER NOT NULL,
    amigo_id INTEGER NOT NULL,
    PRIMARY KEY (jogador_id, amigo_id),
    FOREIGN KEY (jogador_id) REFERENCES JOGADORES(id),
    FOREIGN KEY (amigo_id) REFERENCES JOGADORES(id)
);
insert into JOGO (idalfa, nome, url) 
values 
    ('lol1234567','LeagueOfLegends',  'https://www.leagueoflegends.com/pt-br/'),
    ('rb12345678','Roblox', 'https://www.roblox.com/'),
    ('cs12345678','CounterStrike', 'https://store.steampowered.com/app/730/CounterStrike_Global_Offensive/'),
	('ch12345678','Chess', 'www.chess.com');

 insert into REGIAO (nome) values ('Lisboa'),('Porto'),('Braga'),('Guimaraes'),('Alvor');


insert into PARTIDAS (dtinicio, dtfinal, horainicio, horafim, regiao_nome) values 
    ('2020-01-01','2020-01-01','13:00:00','13:00:00','Lisboa'),
    ('2020-01-02','2020-01-02','14:00:00','14:00:00','Porto'),
    ('2020-01-03','2020-01-03','15:00:00','15:00:00','Braga'),
	('2020-01-04','2020-01-04','16:00:00','16:00:00','Guimaraes'),   
	('2020-01-05','2020-01-05','17:00:00','17:00:00','Alvor');

insert into JOGADORES (username, email, estado, partida_numseq, regiao_nome) values 
('joao','joao1@gmail.com','ATIVO',1,'Lisboa'),
('manu','manu2@gmail.com','INATIVO',2,'Porto'),
('pedro','pedro3@gmail.com','BANIDO',3,'Braga'),
('francis','francis4@gmail.com','ATIVO',4,'Braga');

insert into CONVERSA (jogador_id,nome) values (1,'Geral'), (4,'Geral'),(2,'Jogo'),(3,'Privada');

insert into Partida_MULTIJOGADOR (n_sequencial,regiao_nome, estado) values 
(1,'Lisboa','POR INICIAR'),
(2,'Porto','A AGUARDAR JOGADORES'),
(3,'Braga','EM CURSO'),
(4,'Guimaraes','TERMINADA');

insert into Pontuacao_MULTIJOGADOR (n_sequencial,regiao_nome,id_jogador, pontos,n_partidas) values 
(4,'Guimaraes',1,100,10),
(3,'Braga',2,200,20),
(1,'Lisboa',3,300,30),
(1,'Lisboa',4,400,40);

insert into Partida_NORMAL (n_sequencial,regiao_nome, Grau,id_jogador,pontos,n_partidas) values 
(1,'Lisboa',1,1,100,10),
(2,'Porto',2,2,200,20),
(3,'Braga',3,3,300,30),
(4,'Guimaraes',4,4,400,40);


insert into MENSAGEM ( texto, horaenvio, jogador_id,jogador_username, jogador_email) values 
('ola','2020-01-01 12:00:00',1,'joao','joao1@gmail.com'),
('ola','2020-01-01 12:00:00',2,'manu','manu2@gmail.com'),
('ola','2020-01-01 12:00:00',3,'pedro','pedro3@gmail.com'),
('ola','2020-01-01 12:00:00',4,'francis','francis4@gmail.com');

insert into CRACHA (nome, limitepontos, jogo_id) values 
('Bronze', 100, 'lol1234567'),
('Prata', 200, 'rb12345678'),
('Platina', 300, 'cs12345678'),
('Diamante', 400, 'rb12345678');

insert into JOGADOR_CRACHA (jogo_nome,cracha_nome,jogador_id, jogador_username, jogador_email) values 
('cs12345678','Platina',3,'pedro', 'pedro3@gmail.com'),
('rb12345678','Prata',2 ,'manu', 'manu2@gmail.com');

insert into COMPRA ( jogador_id,jogador_username, jogador_email, jogo_id, data, preço) values 
(1,'joao', 'joao1@gmail.com','lol1234567', '2020-01-01', 10),
(2,'manu', 'manu2@gmail.com','rb12345678', '2020-01-01', 20),
(3,'pedro', 'pedro3@gmail.com','cs12345678', '2020-01-01', 30);

insert into JOGADOR_STATS ( jogador_id,jogador_username, jogador_email, jogo_id, partidasjogadas, pontostotais, totaldeJogosUnicos) values 
(1,'joao', 'joao1@gmail.com','lol1234567', 10, 100, 1),
(2,'manu', 'manu2@gmail.com','rb12345678', 20, 300, 2),
(3,'pedro', 'pedro3@gmail.com','cs12345678', 30, 500, 3),
(4,'francis', 'francis4@gmail.com','rb12345678', 30, 300, 3);


insert into JOGO_STATS (jogo_id, totalpartidas, pontostotais, totaldejogadores) values 
('lol1234567', 10, 100, 1),
('rb12345678', 20, 200, 2),
('cs12345678', 30, 300, 3);

insert into AMIZADE (jogador_id, amigo_id) values 
(1,2),
(2,3),
(3,4);
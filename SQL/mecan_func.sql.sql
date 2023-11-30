--######################## D #########################--
CREATE OR REPLACE PROCEDURE criar_jogador(
    in_username VARCHAR(30),
    in_email VARCHAR(30),
    in_regiao_nome VARCHAR(30)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO JOGADORES (username, email, estado,regiao_nome)
    VALUES (in_username, in_email, 'ATIVO',in_regiao_nome);
END;
$$;
--drop procedure criar_jogador_X(character varying,character varying,character varying);
CREATE OR REPLACE PROCEDURE criar_jogador_X(
    in_username VARCHAR(30),
    in_email VARCHAR(30),
    in_regiao_nome VARCHAR(30)
)
LANGUAGE plpgsql
AS $$
begin
	commit;
	set transaction isolation level REPEATABLE READ;
	Call criar_jogador(in_username,in_email,in_regiao_nome);
end;
$$;
	

CREATE OR REPLACE PROCEDURE desativar_jogador(
    in_jogador_id in smallint
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE JOGADORES SET estado = 'INATIVO'
    WHERE id = in_jogador_id;
END;
$$;
--drop procedure desativar_jogador_X;
CREATE OR REPLACE PROCEDURE desativar_jogador_X(
	in_jogador_id in smallint
)
LANGUAGE plpgsql
AS $$
begin
	commit;
	set transaction isolation level read committed;
	Call desativar_jogador(in_jogador_id);
end;
$$;


CREATE OR REPLACE PROCEDURE banir_jogador(
    in_jogador_id INTEGER
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE JOGADORES SET estado = 'BANIDO'
    WHERE id = in_jogador_id;
END;
$$;


--drop procedure banir_jogador_X;
CREATE OR REPLACE PROCEDURE banir_jogador_X(
	in_jogador_id INTEGER
)
LANGUAGE plpgsql
AS $$
begin
	commit;
	set transaction isolation level read committed;
	Call banir_jogador(in_jogador_id);
end;
$$;

--TESTES
CALL criar_jogador_X('johndoe', 'johndoe5@gmail.com', 'Alvor');
CALL desativar_jogador_X(5);
CALL banir_jogador_X(6);


select * from JOGADORES;
delete from Jogadores where id = 6;

--############################# E ###############################--
CREATE OR REPLACE FUNCTION totalPontosJogador(
    idJogador INTEGER
) RETURNS INTEGER AS $$
DECLARE
    total_pontos_multi INTEGER := 0;
	total_pontos_normi INTEGER := 0;
BEGIN
    IF NOT EXISTS(SELECT * FROM JOGADORES WHERE id = idJogador) THEN
        RAISE EXCEPTION 'Player with ID % does not exist', id_jogador;
    END IF;
    SELECT COALESCE(SUM(pontos), 0) INTO total_pontos_normi
    FROM Partida_NORMAL
    WHERE id_jogador = idJogador;
    SELECT COALESCE(SUM(pontos), 0) INTO total_pontos_multi
    FROM Pontuacao_MULTIJOGADOR
    WHERE id_jogador = idJogador;

    RETURN total_pontos_multi + total_pontos_normi;
END;
$$ LANGUAGE plpgsql;

--drop function totalPontosJogador_X;
CREATE OR REPLACE FUNCTION totalPontosJogador_X(
    idJogador INTEGER
) RETURNS INTEGER AS $$
begin
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
	 return  totalPontosJogador(idJogador);
end;
$$ LANGUAGE plpgsql;


SELECT * FROM totalPontosJogador_X(2); 
--############################# F ###############################--

CREATE OR REPLACE FUNCTION totalJogosJogador(
    idJogador INTEGER
) RETURNS INTEGER AS $$
DECLARE
    total_partidas_multi INTEGER := 0;
	total_partidas_normi INTEGER := 0;
BEGIN
    IF NOT EXISTS(SELECT 1 FROM JOGADORES WHERE id = idJogador) THEN
        RAISE EXCEPTION 'Player with ID % does not exist', id_jogador;
    END IF;
    SELECT COALESCE(SUM(n_partidas), 0) INTO total_partidas_normi
    FROM Partida_NORMAL
    WHERE id_jogador = idJogador;
    SELECT COALESCE(SUM(n_partidas), 0) INTO total_partidas_multi
    FROM Pontuacao_MULTIJOGADOR
    WHERE id_jogador = idJogador;

    RETURN total_partidas_multi + total_partidas_normi;
END;
$$ LANGUAGE plpgsql;

--drop function totalJogosJogador_X;
CREATE OR REPLACE FUNCTION totalJogosJogador_X(
    idJogador INTEGER
) RETURNS INTEGER AS $$
begin
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
	 return  totalJogosJogador(idJogador);
end;
$$ LANGUAGE plpgsql;

SELECT * FROM totalJogosJogador_X(1);
--############################# G ###############################--
CREATE OR REPLACE FUNCTION PontosJogoPorJogador(jogo_ref VARCHAR)
RETURNS TABLE (id_jogador INTEGER, total_pontos INTEGER) AS $$
BEGIN
    RETURN QUERY
    SELECT j.id::INTEGER, CAST(SUM(js.pontostotais) AS INTEGER)
    FROM JOGADORES j
    JOIN JOGADOR_STATS js ON j.username = js.jogador_username AND j.email = js.jogador_email
    WHERE js.jogo_id = jogo_ref
    GROUP BY j.id;
END;
$$ LANGUAGE plpgsql;

--drop function PontosJogoPorJogador_X;
CREATE OR REPLACE FUNCTION PontosJogoPorJogador_X(
    jogo_ref VARCHAR
)returns TABLE (id_jogador INTEGER, total_pontos INTEGER) AS $$
begin
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
	 return query 
	 select * from PontosJogoPorJogador(jogo_ref);
end;
$$ LANGUAGE plpgsql;


SELECT *FROM PontosJogoPorJogador_X('lol1234567');
--######################### H ##########################


CREATE OR REPLACE PROCEDURE associarCracha(
    IN idJogador INT, 
    IN nomeJogo VARCHAR(10),
    IN nomeCracha VARCHAR(50)
) 
AS $$
DECLARE
    pontosJogador INT;
    pontosCracha INT;
    crachaID INT;
    jogador_username VARCHAR(50);
    jogador_email VARCHAR(100);
BEGIN
    BEGIN
		SELECT total_pontos INTO pontosJogador FROM PontosJogoPorJogador(nomeJogo) WHERE id_jogador = idJogador;
        SELECT limitepontos INTO pontosCracha FROM CRACHA WHERE nome = nomeCracha AND jogo_id = nomeJogo;
        SELECT username, email INTO jogador_username, jogador_email FROM JOGADORES WHERE id = idJogador;
        
        IF pontosJogador >= pontosCracha THEN
            INSERT INTO JOGADOR_CRACHA ( jogo_nome,cracha_nome, jogador_id, jogador_username, jogador_email)
            VALUES (nomeJogo, nomeCracha, idJogador, jogador_username, jogador_email);
            
            RAISE NOTICE 'Crachá atribuído com sucesso!';
        ELSE
            RAISE EXCEPTION 'O jogador não tem pontos suficientes para ganhar este crachá.';
            ROLLBACK;
        END IF;
        
    EXCEPTION WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
    END;
END;
$$ LANGUAGE plpgsql;


--drop procedure associarCracha_X;
CREATE OR REPLACE PROCEDURE associarCracha_X(
    IN idJogador INT, 
    IN nomeJogo VARCHAR(50),
    IN nomeCracha VARCHAR(50)
)
LANGUAGE plpgsql
AS $$
begin
	commit;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	 call  associarCracha(idJogador, nomeJogo, nomeCracha);
end;
$$ ;

CALL associarCracha_X(1, 'lol1234567', 'Bronze');

select * from CRACHA;
select * from JOGADOR_STATS;
select * from JOGADOR_CRACHA;

UPDATE CRACHA
SET limitepontos = 100
WHERE cracha_id = 1;
--######################### I ##########################

CREATE OR REPLACE PROCEDURE iniciar_conversa(
		IN jogador_id INTEGER,
		IN nome_conversa VARCHAR(20),
		OUT conversa_id INTEGER
	)
AS $$
	DECLARE
		jogador_username VARCHAR(30);
		jogador_email VARCHAR(30);
	BEGIN
		SELECT username INTO jogador_username FROM jogadores WHERE id = jogador_id;
		SELECT email INTO jogador_email FROM jogadores WHERE id = jogador_id;

		IF jogador_username IS NULL THEN
			RAISE EXCEPTION 'Jogador com id % nao existe', jogador_id;
		END IF;

		IF jogador_email IS NULL THEN
			RAISE EXCEPTION 'Jogador com id % nao existe', jogador_id;
		END IF;

		INSERT INTO conversa (jogador_id, nome) VALUES (jogador_id, nome_conversa);
		SELECT lastval() INTO conversa_id;

		INSERT INTO mensagem (
			conversa_id, ordem, texto, horaenvio, jogador_id, jogador_username, jogador_email
		) VALUES (
			conversa_id, 1, 'Nova conversa iniciada', NOW(), jogador_id, jogador_username, jogador_email
		);
	END;
$$ LANGUAGE plpgsql;

--drop procedure iniciar_conversa_X;
CREATE OR REPLACE PROCEDURE iniciar_conversa_X(
    IN jogador_id INTEGER,
	IN nome_conversa VARCHAR(20),
	OUT conversa_id INTEGER
)
LANGUAGE plpgsql
AS $$
begin
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
	call  iniciar_conversa(jogador_id, nome_conversa, conversa_id);
end;
$$ ;

--TESTES
BEGIN;
DO $$
DECLARE
  conversa_id INTEGER;
BEGIN
  CALL iniciar_conversa_X(4, 'Privada', conversa_id);
  RAISE NOTICE 'Conversa criada com ID %', conversa_id;
END;
$$;
commit;
select * from conversa;
select * from MENSAGEM;



--######################### J ##########################

CREATE OR REPLACE PROCEDURE juntarConversa(
    p_jogador_id INTEGER,
    p_chat_id INTEGER
) AS $$
BEGIN
    IF p_jogador_id IS NULL THEN
        RAISE EXCEPTION 'Jogador com id % nao existe', p_jogador_id;
    END IF;
    
    IF p_chat_id IS NULL THEN
        RAISE EXCEPTION 'Conversa com id % nao existe', p_chat_id;
    END IF;
    
    INSERT INTO CONVERSA (chat_id, jogador_id, nome) 
    VALUES (p_chat_id, p_jogador_id, (SELECT nome FROM CONVERSA WHERE id = p_jogador_id));
END;
$$ LANGUAGE plpgsql;


--drop procedure juntarConversa_X;
CREATE OR REPLACE PROCEDURE juntarConversa_X(
    p_jogador_id INTEGER,
    p_conversa_id INTEGER
)
LANGUAGE plpgsql
AS $$
begin
	commit;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	call  juntarConversa(p_jogador_id, p_conversa_id);
end;
$$ ;

--TESTES
call juntarConversa_X(2,1);
select * from conversa;

--######################### K ##########################

CREATE OR REPLACE PROCEDURE enviarMensagem(
    p_jogador_id INTEGER,
    p_conversa_id INTEGER,
    p_texto_mensagem TEXT
) AS $$
DECLARE
    jogador_username VARCHAR(30);
	jogador_email VARCHAR(30);
BEGIN
	SELECT username INTO jogador_username FROM jogadores WHERE id = p_jogador_id;
	SELECT email INTO jogador_email FROM jogadores WHERE id = p_jogador_id;
    INSERT INTO MENSAGEM (conversa_id,texto, horaenvio, jogador_id,jogador_username, jogador_email)
    VALUES ( p_conversa_id, p_texto_mensagem, NOW(), p_jogador_id, jogador_username,jogador_email );
END;
$$ LANGUAGE plpgsql;


--drop procedure enviarMensagem_X;
CREATE OR REPLACE PROCEDURE enviarMensagem_X(
    p_jogador_id INTEGER,
    p_conversa_id INTEGER,
    p_texto_mensagem TEXT
)
LANGUAGE plpgsql
AS $$
begin
	commit;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	call  enviarMensagem(p_jogador_id, p_conversa_id, p_texto_mensagem);
end;
$$ ;

--TESTES
CALL enviarMensagem_X (1, 3, 'Olá, pessoal! Tudo bem?');
select * from mensagem;


--######################### L ##########################
CREATE VIEW jogadorTotalInfo AS
SELECT
    j.id AS id_jogador,
    j.estado,
    j.email,
    j.username,
    COUNT(DISTINCT pn.n_sequencial) + COUNT(DISTINCT pm.n_sequencial) AS total_jogos,
    COUNT(pn.n_partidas) + COUNT(pm.n_partidas) AS total_partidas,
    SUM(pn.pontos) + SUM(pm.pontos) AS total_pontos
FROM
    JOGADORES j
    LEFT JOIN Partida_NORMAL pn ON j.id = pn.id_jogador
    LEFT JOIN Pontuacao_MULTIJOGADOR pm ON j.id = pm.n_sequencial
WHERE
    j.estado != 'BANIDO'
GROUP BY
    j.id;

--TESTES
SELECT * FROM jogadorTotalInfo ;

--######################### M ##########################
CREATE OR REPLACE FUNCTION atribuir_crachas() RETURNS TRIGGER AS $$
DECLARE
    jogador_id INTEGER;
    jogo_nome VARCHAR(50);
    pontos_totais INTEGER;
    cracha_nome VARCHAR(50);
BEGIN
    SELECT Partida_MULTIJOGADOR.regiao_nome, Partida_MULTIJOGADOR.n_sequencial INTO jogo_nome, jogador_id
    FROM Partida_MULTIJOGADOR
    WHERE Partida_MULTIJOGADOR.n_sequencial = OLD.n_sequencial AND Partida_MULTIJOGADOR.regiao_nome = OLD.regiao_nome;
    SELECT SUM(pontos) INTO pontos_totais
    FROM Pontuacao_MULTIJOGADOR
    WHERE n_sequencial = OLD.n_sequencial AND regiao_nome = OLD.regiao_nome AND id_jogador = jogador_id;
    IF pontos_totais >= 400 THEN
        cracha_nome := 'Diamante';
    ELSIF pontos_totais >= 300 and pontos_totais < 400 THEN
        cracha_nome := 'Platina';
    ELSIF pontos_totais >= 200 and pontos_totais< 300 Then 
        cracha_nome := 'Prata';
	Else
		cracha_nome := 'Bronze';
    END IF;
    INSERT INTO JOGADOR_CRACHA(cracha_nome, jogador_id, jogador_username, jogador_email)
    SELECT cracha_nome, id, username, email FROM JOGADORES WHERE id = jogador_id;
    
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER atribuir_crachas AFTER UPDATE ON Partida_MULTIJOGADOR
FOR EACH ROW
WHEN (OLD.estado <> 'TERMINADA' AND NEW.estado = 'TERMINADA')
EXECUTE FUNCTION atribuir_crachas();

select * from CRACHA;
--######################### N ##########################
CREATE OR REPLACE FUNCTION ban_jogadorTotalInfo() RETURNS TRIGGER as $$
BEGIN
      UPDATE jogadores SET estado = 'BANIDO' WHERE id = OLD.id_jogador;
	  RETURN OLD;
END;
$$ language plpgsql;

   CREATE TRIGGER ban_jogadorTotalInfo_trigger
   INSTEAD OF DELETE ON jogadorTotalInfo
   FOR EACH ROW
   EXECUTE FUNCTION ban_jogadorTotalInfo();
END; 
--###################### 2b exception ###########################################

UPDATE CRACHA
SET limitepontos = 100
WHERE cracha_id = 1;

select * from CRACHA;
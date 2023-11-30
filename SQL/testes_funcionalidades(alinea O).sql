--################################################## O #####################################################

--######################### D ##########################
do language plpgsql
$$
--Cenário Normal!
	begin
	   begin
		CALL criar_jogador_X(6, 'richy', 'richy5@gmail.com', 4, 'Lisboa');
        RAISE NOTICE 'Teste 2d_1: Inserir jogador com dados bem passados: Resultado OK';
    	EXCEPTION 
       	   WHEN OTHERS THEN 
            RAISE NOTICE 'Teste 2d_1: Inserir jogador com dados bem passados: Resultado FAIL';
    END;
	BEGIN
    	CALL desativar_jogador_X(6);
        RAISE NOTICE 'Teste 2d_2: Torcar modo para desativar Resultado OK';
    	EXCEPTION 
        	WHEN OTHERS THEN 
            	RAISE NOTICE 'Teste 2d_2: Torcar modo para desativar Resultado FAIL';
    END;
	BEGIN
		CALL banir_jogador_X(6);
        RAISE NOTICE 'Teste 2d_3: Banir Jogador com o id: Resultado OK';
    	EXCEPTION 
        	WHEN OTHERS THEN 
            	RAISE NOTICE 'Teste 2d_3: Banir Jogador com o id: Resultado FAIL';
     END;
	END;
$$;

--Delete from JOGADORES where id = 6;
--select * from JOGADORES;


--Cenário de ERRO.

do language plpgsql
$$
BEGIN 
    BEGIN
        CALL criar_jogador(2, 'manu', 'manu2@gmail.com', 2, 'Porto');
        RAISE NOTICE 'Teste 2d_1: Inserir jogador com dados bem passados: Resultado OK';
    	EXCEPTION 
        	WHEN OTHERS THEN 
            	RAISE NOTICE 'Teste 2d_1: Inserir jogador com dados bem passados: Resultado FAIL';
    END;
    BEGIN
        CALL desativar_jogador(7);
        RAISE NOTICE 'Teste 2d_2: Torcar modo para desativar: Resultado FAIL';
    	EXCEPTION 
        	WHEN OTHERS THEN 
				RAISE NOTICE 'Teste 2d_2: Torcar modo para desativar Resultado OK';
    END;
    BEGIN
        CALL banir_jogador(7);
        RAISE NOTICE 'Teste 2d_3: Banir Jogador com o id: Resultado FAIL';
    	EXCEPTION 
        	WHEN OTHERS THEN 
            	RAISE NOTICE 'Teste 2d_3: Banir Jogador com o id: Resultado OK';
   	  END;
   END;
$$;
--######################### E ##########################

--CENARIO NORMAL
DO $$ 
BEGIN 
    BEGIN
        IF (totalPontosJogador_X(2) = 400) then
            RAISE NOTICE 'Teste 2e: Verificar o numero de pontos do Jogador: Resultado OK';
        ELSE
            RAISE NOTICE 'Teste 2e: Verificar o numero de pontos do Jogador: Resultado FAIL';
        END IF;
    END;
END $$ LANGUAGE plpgsql;

--select * from totalPontosJogador_X(2);

--CENARIO ERRO
DO $$ 
BEGIN 
    BEGIN
        IF (totalPontosJogador_X(2) = 300) then
            RAISE NOTICE 'Teste 2e:Verificar o numero de pontos do Jogador: Resultado OK';
        ELSE
            RAISE NOTICE 'Teste 2e: Verificar o numero de pontos do Jogador: Resultado FAIL';
        END IF;
    END;
END $$ LANGUAGE plpgsql;

--######################### F ##########################
--CENARIO NORMAL
DO $$ 
BEGIN 
    BEGIN
        IF (totalJogosJogador_X(1) = 20) then
            RAISE NOTICE 'Teste 2f: Numero de partidas de um jogador: Resultado OK';
        ELSE
            RAISE NOTICE 'Teste 2f: Numero de partidas de um jogador: Resultado FAIL';
        END IF;
    END;
END $$ LANGUAGE plpgsql;


--select * from totalJogosJogador_X(1);
--CENARIO ERRO
DO $$ 
BEGIN 
    BEGIN
        IF (totalJogosJogador_X(1) = 30) then
            RAISE NOTICE 'Teste 2f: Numero de partidas de um jogador: Resultado OK';
        ELSE
            RAISE NOTICE 'Teste 2e: Numero de partidas de um jogador: Resultado FAIL';
        END IF;
    END;
END $$ LANGUAGE plpgsql;

--######################### G ##########################
--CENARIO NORMAL
DO $$ 
BEGIN 
    BEGIN
        DECLARE 
            v_id_jogador INTEGER;
            v_total_pontos INTEGER;
        BEGIN
            SELECT id_jogador, total_pontos INTO v_id_jogador, v_total_pontos 
            FROM PontosJogoPorJogador_X('League of Legends');
            
            IF (v_id_jogador = 1 and v_total_pontos = 100 ) THEN
                RAISE NOTICE 'Teste 2g: Pontos no jogo de cada jogador: Resultado OK';
            ELSE
                RAISE NOTICE 'Teste 2g: Pontos no jogo de cada jogador: Resultado FAIL';
            END IF;
        END;
    END;
END $$ LANGUAGE plpgsql;

--SELECT *FROM PontosJogoPorJogador_X('League of Legends')

--CENARIO ERRO
DO $$ 
BEGIN 
    BEGIN
        DECLARE 
            v_id_jogador INTEGER;
            v_total_pontos INTEGER;
        BEGIN
            SELECT id_jogador, total_pontos INTO v_id_jogador, v_total_pontos 
            FROM PontosJogoPorJogador_X('League of Legends');
            
            IF (v_id_jogador = 1 and v_total_pontos = 250 ) THEN
                RAISE NOTICE 'Teste 2g: Pontos no jogo de cada jogador: Resultado OK';
            ELSE
                RAISE NOTICE 'Teste 2g: Pontos no jogo de cada jogador: Resultado FAIL';
            END IF;
        END;
    END;
END $$ LANGUAGE plpgsql;

--SELECT *FROM PontosJogoPorJogador_X('League of Legends')

--######################### H ##########################
--Cenário de Normal.

do language plpgsql
$$
BEGIN 
    BEGIN
        CALL associarCracha_X(1, 'League of Legends', 'Bronze');
        RAISE NOTICE 'Teste 2h: Associar Crachá a Jogador: Resultado OK';
    	EXCEPTION 
        	WHEN OTHERS THEN 
            	RAISE NOTICE 'Teste 2h: Associar Crachá a Jogador: Resultado FAIL ';
    END;
END $$;

select * from JOGADOR_CRACHA;
--Cenário de FAIL

do language plpgsql
$$
BEGIN 
    BEGIN
        CALL associarCracha_X(2, 'League of Legends', 'Bronze');
        RAISE NOTICE 'Teste 2h: Associar Crachá a Jogador: Resultado OK';
    	EXCEPTION 
        	WHEN OTHERS THEN 
            	RAISE NOTICE 'Teste 2h:  Associar Crachá a Jogador: Resultado FAIL';
    END;
END $$;

--######################### I ##########################
--Cenário de Normal.

DO $$
DECLARE
  conversa_id INTEGER;
BEGIN 
    BEGIN
        CALL iniciar_conversa_X(4, 'NOVA CONVERSA', conversa_id);
        RAISE NOTICE 'Teste 2i: Conversa criada com ID % : Resultado OK', conversa_id;
    EXCEPTION 
        WHEN OTHERS THEN 
            RAISE NOTICE 'Teste 2i: Conversa criada com o id: Resultado FAIL';           
    END;
END $$ LANGUAGE plpgsql;

--Cenário de FAIL

DO $$
DECLARE
  conversa_id INTEGER;
BEGIN 
    BEGIN
        CALL iniciar_conversa_X(39, 'NOVA CONVERSA', conversa_id);
        RAISE NOTICE 'Teste 2i: Conversa criada com ID % : Resultado OK', conversa_id;
    EXCEPTION 
        WHEN OTHERS THEN 
            RAISE NOTICE 'Teste 2i: Conversa criada com o id: Resultado FAIL';           
    END;
END $$ LANGUAGE plpgsql;

--######################### J ##########################
--Cenário de Normal.

DO $$
BEGIN 
    BEGIN
        CALL juntarConversa_X(2,1);
        RAISE NOTICE 'Teste 2J: Juntar à Conversa: Resultado OK';
    EXCEPTION 
        WHEN OTHERS THEN 
            RAISE NOTICE 'Teste 2J: Juntar à Conversa: Resultado FAIL';           
    END;
END $$ LANGUAGE plpgsql;

SELECT * FROM conversa;


--Cenário de FAIL

DO $$
DECLARE
  conversa_id INTEGER;
BEGIN 
    BEGIN
        CALL juntarConversa_X(39,58);
        RAISE NOTICE 'Teste 2J: Juntar à Conversa: Resultado OK';
    EXCEPTION 
        WHEN OTHERS THEN 
            RAISE NOTICE 'Teste 2J: Juntar à Conversa: Resultado FAIL';           
    END;
END $$ LANGUAGE plpgsql;

--######################### K ##########################
--Cenário de Normal.

DO $$
BEGIN 
    BEGIN
        CALL enviarMensagem_X(1, 3, 'Olá, pessoal! Tudo bem?');
        RAISE NOTICE 'Teste 2K: Mensagem enviada: Resultado OK';
    EXCEPTION 
        WHEN OTHERS THEN 
            RAISE NOTICE 'Teste 2K: Mensagem enviada: Resultado FAIL';           
    END;
END; 
$$ LANGUAGE plpgsql;
--select * from mensagem;
--Cenário de ERRO.

DO $$
BEGIN 
    BEGIN
        CALL enviarMensagem_X(22, 32, 'Olá, pessoal! Tudo bem?');
        RAISE NOTICE 'Mensagem enviada : Resultado OK';
    EXCEPTION 
        WHEN OTHERS THEN 
            RAISE NOTICE 'Mensagem enviada: Resultado FAIL';           
    END;
END $$ LANGUAGE plpgsql;

--######################### M ##########################
--Cenário Normal!
do language plpgsql
$$
declare
	idJogador INTEGER := 0;
begin
   begin
   	   SELECT id_jogador INTO idJogador
       FROM Pontuacao_MULTIJOGADOR
       WHERE n_sequencial = 3;
	   
	   UPDATE Partida_MULTIJOGADOR
	   SET estado = 'TERMINADA'
	   WHERE n_sequencial = 3;
       IF EXISTS (SELECT * FROM JOGADOR_CRACHA WHERE jogador_id = idJogador) THEN
        		RAISE NOTICE 'Teste 2m: Cracha successfully granted: RESULT OK';
			ELSE 
				RAISE NOTICE 'Teste 2m: Cracha successfully granted: RESULT FAIL';
			end if;	
		end;
	end;
$$;

SELECT * FROM JOGADOR_CRACHA
--select * from Partida_MULTIJOGADOR

--Cenário ERRO!
do language plpgsql
$$
declare
	idJogador INTEGER := 0;
begin
   begin
   	   SELECT id_jogador INTO idJogador
       FROM Pontuacao_MULTIJOGADOR
       WHERE n_sequencial = 2;
	   
	   UPDATE Partida_MULTIJOGADOR
	   SET estado = 'POR INICIAR'
	   WHERE n_sequencial = 2;
       IF exists (SELECT * FROM JOGADOR_CRACHA WHERE jogador_id = idJogador) THEN
        		RAISE NOTICE 'Teste 2m: Cracha successfully granted: RESULT OK';
			ELSE 
				RAISE NOTICE 'Teste 2m: Cracha successfully granted: RESULT FAIL';
			end if;	
		end;
	end;
$$;
--######################### N ##########################

--Cenário Normal!
do language plpgsql
$$
begin
   begin
	    DELETE from jogadorTotalInfo WHERE id_jogador = 1;
    		IF EXISTS (SELECT * FROM jogadores WHERE id = 1 AND estado = 'BANIDO') THEN
        		RAISE NOTICE 'Teste 2n: Player successfully banned: RESULT OK';
			ELSE 
				RAISE NOTICE 'Teste 2n: Player  banned: RESUTLT FAIL';
			end if;	
		end;
	end;
$$;
--Cenário ERRO!	

do language plpgsql
$$
begin	
	begin
	    DELETE FROM jogadorTotalInfo WHERE id_jogador = 8;
    		IF EXISTS (SELECT * FROM jogadores WHERE id = 8 AND estado = 'BANIDO') THEN
        		RAISE NOTICE 'Teste 2n: Player successfully banned: RESULT OK';
			ELSE 
				RAISE NOTICE 'Teste 2n: Player  banned: RESUTLT FAIL';
			end if;
		end;	
	end;
$$;

--SELECT * FROM JOGADORES;
--SELECT * FROM jogadorTotalInfo ;
/*
 Walter Vieira (2022-04-22)
 Sistemas de Informa��o - projeto JPAAulas_ex1
 C�digo desenvolvido para iulustra��o dos conceitos sobre acesso a dados, concretizados com base na especifica��o JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE vers�o 2022-03 (4.23.0).
 
N�o existe a pretens�o de que o c�digo estaja completo.

Embora tenha sido colocado um esfor�o significativo na corre��o do c�digo, n�o h� garantias de que ele n�o contenha erros que possam 
acarretar problemas v�rios, em particular, no que respeita � consist�ncia dos dados.  
 
*/

package businessLogic;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import jakarta.persistence.*;
import model.*;

import java.util.Scanner;


public class BLService {
    public void criarJogador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o username do jogador:");
        String username = scanner.next();

        System.out.print("Digite o email do jogador:");
        String email = scanner.next();

        System.out.print("Digite a regiao do jogador:");
        String regiao = scanner.next();
        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String sql = "call criar_jogador(? , ? , ?)";
            CallableStatement criarJogadorQuery = connection.prepareCall(sql);
            criarJogadorQuery.setString(1, username);
            criarJogadorQuery.setString(2, email);
            criarJogadorQuery.setString(3, regiao);

            criarJogadorQuery.executeUpdate();

            em.getTransaction().commit();

            System.out.println("Jogador criado com sucesso!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void desativarJogador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o id do jogador:");
        int playerId = scanner.nextInt();

        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String sql = "call desativar_jogador(?)";
            CallableStatement desativarJogador = connection.prepareCall(sql);
            desativarJogador.setInt(1, playerId);

            desativarJogador.executeUpdate();

            em.getTransaction().commit();

            System.out.println("Jogador Desativado com sucesso!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void banirJogador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o id do jogador:");
        int playerId = scanner.nextInt();
        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String sql = "call banir_jogador(?)";
            CallableStatement desativarJogador = connection.prepareCall(sql);
            desativarJogador.setInt(1, playerId);

            desativarJogador.executeUpdate();

            em.getTransaction().commit();

            System.out.println("Jogador Banido com sucesso!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void obterTotalPontosJogador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o id do jogador:");
        Integer playerId = scanner.nextInt();

        try {
            em.getTransaction().begin();
            StoredProcedureQuery totalPontos =
                    em.createStoredProcedureQuery("totalPontosJogador");
            totalPontos.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            totalPontos.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);

            totalPontos.setParameter(1, playerId);
            totalPontos.execute();
            String finalPoints =  totalPontos.getOutputParameterValue(2).toString();
            em.getTransaction().commit();

            System.out.println("Jogador tem um total de " + finalPoints + " pontos!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void obterTotalJogosJogador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o id do jogador:");
        Integer playerId = scanner.nextInt();
        try {
            em.getTransaction().begin();
            StoredProcedureQuery totalJogos =
                    em.createStoredProcedureQuery("totalJogosJogador");
            totalJogos.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            totalJogos.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);

            totalJogos.setParameter(1, playerId);
            totalJogos.execute();
            String finalGames =  totalJogos.getOutputParameterValue(2).toString();
            em.getTransaction().commit();

            System.out.println("Jogador tem um total de " + finalGames + " jogos!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void obterPontosPorJogadorEmJogo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a referencia do Jogo:");
        String gameRef = scanner.next();

        try {
            em.getTransaction().begin();
            StoredProcedureQuery ppg =
                    em.createStoredProcedureQuery("PontosJogoPorJogador");
            ppg.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            ppg.setParameter(1, gameRef);
            ppg.execute();
            List<Object[]> l = (List<Object[]>) ppg.getResultList();
            for(Object[] x : l) {
                System.out.printf("->Valor ID: %d\n->Valor Pontos: %d\n", (Integer)x[0], (Integer)x[1]);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            //throw e;
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void associarCrachaJogador1() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o id do jogador:");
        Integer id = scanner.nextInt();

        System.out.print("Digite o nome do Jogo:");
        String nomeJogo = scanner.next();

        System.out.print("Digite o Nome do Crachá:");
        String nomeCracha = scanner.next();

        try {
            em.getTransaction().begin();

            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            String sql2 = "call associarCracha(? , ? , ?)";
            CallableStatement criarJogadorQuery = connection.prepareCall(sql2);
            criarJogadorQuery.setInt(1, id);
            criarJogadorQuery.setString(2, nomeJogo);
            criarJogadorQuery.setString(3, nomeCracha);

            criarJogadorQuery.executeUpdate();

            em.getTransaction().commit();

            System.out.println("Crachá associado com sucesso!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }
    public void associarCrachaJogador2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do jogador: ");
        int idJogador = scanner.nextInt();

        System.out.print("Digite o nome do jogo: ");
        String nomeJogo = scanner.next();

        System.out.print("Digite o nome do crachá: ");
        String nomeCracha = scanner.next();

        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            JogadorStats pontosJogador = em.createQuery(
                            "SELECT js FROM JogadorStats js WHERE js.jogadorId = ?1 AND js.jogoId = ?2 "
                            , JogadorStats.class)
                    .setParameter(1, idJogador)
                    .setParameter(2, nomeJogo)
                    .getSingleResult();

            Cracha crachaTable =
                    em.createQuery(
                            "SELECT c FROM Cracha c WHERE c.nomeCracha = ?1 and c.jogoId = ?2 ",
                                    Cracha.class)
                            .setParameter(1, nomeCracha)
                            .setParameter(2, nomeJogo)
                            .getSingleResult();

            JogadorStats jogador = em.find(JogadorStats.class, idJogador);
            String username = jogador.getJogadorUsername();
            String email = jogador.getJogadorEmail();

            if (pontosJogador.getPontosTotais() >= crachaTable.getLimitePontos()) {
                JogadorCracha jogadorCracha = new JogadorCracha();
                jogadorCracha.setJogoNome(nomeJogo);
                jogadorCracha.setCrachaNome(nomeCracha);
                jogadorCracha.setJogadorId(idJogador);
                jogadorCracha.setJogadorUsername(username);
                jogadorCracha.setJogadorEmail(email);

                em.persist(jogadorCracha);
                em.getTransaction().commit();

                System.out.println("Crachá atribuído com sucesso!");
            } else {
                System.out.println("O jogador não tem pontos suficientes para ganhar este crachá.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }
    }
    public void associarCrachaJogador3() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do jogador: ");
        int idJogador = scanner.nextInt();

        System.out.print("Digite o nome do jogo: ");
        String nomeJogo = scanner.next();

        System.out.print("Digite o nome do crachá: ");
        String nomeCracha = scanner.next();

        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            // Call the PontosJogoPorJogador function using SQL
            String sql = "SELECT total_pontos FROM PontosJogoPorJogador(?) WHERE id_jogador = ?;";
            Query query = em.createNativeQuery(sql); // Specify the expected return type
            query.setParameter(1, nomeJogo);
            query.setParameter(2, idJogador);
            Integer pontosJogador = (Integer) query.getSingleResult(); // Cast the result to int

            System.out.printf("Pontos do jogador: %d\n", pontosJogador);

            Cracha crachaTable = em.createQuery(
                            "SELECT c FROM Cracha c WHERE c.nomeCracha = ?1 AND c.jogoId = ?2",
                            Cracha.class)
                    .setParameter(1, nomeCracha)
                    .setParameter(2, nomeJogo)
                    .getSingleResult();

            JogadorStats jogador = em.find(JogadorStats.class, idJogador);
            String username = jogador.getJogadorUsername();
            String email = jogador.getJogadorEmail();

            if (pontosJogador >= crachaTable.getLimitePontos()) {
                JogadorCracha jogadorCracha = new JogadorCracha();
                jogadorCracha.setJogoNome(nomeJogo);
                jogadorCracha.setCrachaNome(nomeCracha);
                jogadorCracha.setJogadorId(idJogador);
                jogadorCracha.setJogadorUsername(username);
                jogadorCracha.setJogadorEmail(email);

                em.persist(jogadorCracha);
                em.getTransaction().commit();

                System.out.println("Crachá atribuído com sucesso!");
            } else {
                System.out.println("O jogador não tem pontos suficientes para ganhar este crachá.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }




    public void iniciarConversa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o id do jogador:");
        Integer id = scanner.nextInt();

        System.out.print("Digite o nome da Conversa:");
        String nomeConversa = scanner.next();

        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String sql = "call iniciar_conversa(?,?,?)";
            CallableStatement query = connection.prepareCall(sql);
            query.setInt(1, id);
            query.setString(2, nomeConversa);
            query.registerOutParameter(3, Types.INTEGER);

            query.executeUpdate();
            em.getTransaction().commit();

            System.out.printf("Conversa criada com sucesso! O jogador %s criou a conversa com o id %d\n",id, query.getInt(3));

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }


    public void juntarJogadorConversa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o id do jogador:");
        int idJogador = scanner.nextInt();

        System.out.print("Digite o id da Conversa:");
        int idConversa = scanner.nextInt();


        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String sql = "call juntarConversa(?, ?)";
            CallableStatement criarJogadorQuery = connection.prepareCall(sql);

            criarJogadorQuery.setInt(1, idJogador);
            criarJogadorQuery.setInt(2, idConversa);

            criarJogadorQuery.executeUpdate();

            em.getTransaction().commit();

            System.out.println("Conversa juntada com sucesso!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void enviarMensagemConversa() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o id do jogador:");
        int idJogador = scanner.nextInt();

        System.out.print("Digite o id da Conversa:");
        int idConversa = scanner.nextInt();

        System.out.print("Digite o Texto da Conversa:");
        String texto = scanner.next();

        try {

            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String sql  ="call enviarMensagem(?, ?, ?)";
            CallableStatement criarJogadorQuery = connection.prepareCall(sql);

            criarJogadorQuery.setInt(1, idJogador);
            criarJogadorQuery.setInt(2, idConversa);
            criarJogadorQuery.setString(3, texto);

            criarJogadorQuery.executeUpdate();

            em.getTransaction().commit();

            System.out.println("Mensagem enviada com sucesso!");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void acessarInformacoesCompletasJogador() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        String sql = "SELECT jti FROM model.JogadorTotalInfo jti";
        Query query = em.createQuery(sql);
        List<JogadorTotalInfo> la = query.getResultList();

        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            for (JogadorTotalInfo jogadorTotalInfo : la) {
                System.out.println("ID Jogador: " + jogadorTotalInfo.getIdJogador());
                System.out.println("Estado: " + jogadorTotalInfo.getEstado());
                System.out.println("Email: " + jogadorTotalInfo.getEmail());
                System.out.println("Username: " + jogadorTotalInfo.getUsername());
                System.out.println("Total Jogos: " + jogadorTotalInfo.getTotalJogos());
                System.out.println("Total Partidas: " + jogadorTotalInfo.getTotalPartidas());
                System.out.println("Total Pontos: " + jogadorTotalInfo.getTotalPontos());
                System.out.println();
            }

            em.getTransaction().commit();

            System.out.println("Informações completas do jogador acessadas com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    public void incrementarPontosCrachaOptimistic() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o nome do Cracha:");
        String badgeName = scanner.next();
        System.out.print("Insira o nome do Jogo:");
        String gameID = scanner.next();

        try {
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            String jpql = "SELECT c FROM Cracha c WHERE c.nomeCracha = ?1 AND c.jogoId= ?2";
            TypedQuery<Cracha> query = em.createQuery(jpql, Cracha.class);
            query.setParameter(1, badgeName);
            query.setParameter(2, gameID);
            Cracha cracha = query.getSingleResult();

            //Thread.sleep(7000);
            // Dar update ao limite de pontos do Cracha no pgAdmin para que haja
            // um conflito de atualização e o OptimisticLockException seja lançado
            /*
                UPDATE CRACHA
                SET limitepontos = 100
                WHERE cracha_id = 1;
             */

            int currentPoints = cracha.getLimitePontos();
            int increasedPoints = (int) (currentPoints * 1.2);
            cracha.setLimitePontos(increasedPoints);

            em.merge(cracha);
            em.getTransaction().commit();
            System.out.println("Pontos aumentados com sucesso!");
        } catch (NoResultException e) {
            System.out.println("Crachá não encontrado");
        } catch (OptimisticLockException e) {
            System.out.println("ERRO:" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    public void incrementarPontosCrachaPessimistic() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAEx");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o nome do Crachá:");
        String nomeCracha = scanner.next();
        System.out.print("Insira o nome do Jogo:");
        String nomeJogo = scanner.next();

        try {
            em.getTransaction().begin();

            String jpql = "SELECT c FROM Cracha c WHERE c.nomeCracha = :nomeCracha AND c.jogoId = :nomeJogo";
            TypedQuery<Cracha> query = em.createQuery(jpql, Cracha.class);
            query.setParameter("nomeCracha", nomeCracha);
            query.setParameter("nomeJogo", nomeJogo);
            query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
            Cracha cracha = query.getSingleResult();
            em.refresh(cracha);

            int currentPoints = cracha.getLimitePontos();
            int increasedPoints = (int) (currentPoints * 1.2);
            cracha.setLimitePontos(increasedPoints);

            em.getTransaction().commit();

            System.out.println("Pontos aumentados com sucesso!");
        } catch (NoResultException e) {
            System.out.println("Crachá não encontrado");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

}

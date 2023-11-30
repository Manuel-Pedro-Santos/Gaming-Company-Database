package model;


import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "jogadorTotalInfo")
@NamedNativeQuery(name = "JogadorTotalInfo.findAll", query = "SELECT jti FROM jogadorTotalInfo jti", resultClass = JogadorTotalInfo.class)
public class JogadorTotalInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_jogador")
    private int idJogador;

    private String estado;
    private String email;
    private String username;

    @Column(name = "total_jogos")
    private int totalJogos;

    @Column(name = "total_partidas")
    private int totalPartidas;

    @Column(name = "total_pontos")
    private int totalPontos;

    public JogadorTotalInfo() {
    }

    public int getIdJogador() {
        return this.idJogador;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }


    public int getTotalJogos() {
        return this.totalJogos;
    }

    public int getTotalPartidas() {
        return this.totalPartidas;
    }


    public int getTotalPontos() {
        return this.totalPontos;
    }

}


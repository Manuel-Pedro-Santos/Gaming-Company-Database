package model;

import jakarta.persistence.*;

@Entity
@Table(name = "pontuacao_multijogador")
public class PontuacaoMultijogador {
    @Id
    @Column(name = "id_jogador", nullable = false)
    private Integer id;


    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jogador", nullable = false)
    private Integer jogadores;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "n_sequencial", nullable = false)
    private Partida nSequencial;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regiao_nome", nullable = false)
    private Regiao regiaoNome;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "n_partidas", nullable = false)
    private Integer nPartidas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJogadores() {
        return jogadores;
    }

    public void setJogadores(Integer jogadores) {
        this.jogadores = jogadores;
    }

    public Partida getNSequencial() {
        return nSequencial;
    }

    public void setNSequencial(Partida nSequencial) {
        this.nSequencial = nSequencial;
    }

    public Regiao getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(Regiao regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getNPartidas() {
        return nPartidas;
    }

    public void setNPartidas(Integer nPartidas) {
        this.nPartidas = nPartidas;
    }

}
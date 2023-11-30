package model;

import jakarta.persistence.*;

@Entity
@Table(name = "partida_normal")
public class PartidaNormal {
    @Id
    @Column(name = "n_sequencial", nullable = false)
    private Short id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regiao_nome", nullable = false)
    private Regiao regiaoNome;

    @Column(name = "grau", nullable = false)
    private Integer grau;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jogador", nullable = false)
    private Integer idJogador;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "n_partidas", nullable = false)
    private Integer nPartidas;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Regiao getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(Regiao regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public Integer getGrau() {
        return grau;
    }

    public void setGrau(Integer grau) {
        this.grau = grau;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
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
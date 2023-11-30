package model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogo_stats")
public class JogoStat {
    @Id
    @Column(name = "jogo_id", nullable = false, length = 50)
    private String jogoId;

    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogo_nome", nullable = false)
    private String jogoNome;

    @Column(name = "totalpartidas")
    private Integer totalpartidas;

    @Column(name = "pontostotais")
    private Integer pontostotais;

    @Column(name = "totaldejogadores")
    private Integer totaldejogadores;

    public String getJogoNome() {
        return jogoNome;
    }

    public void setJogoNome(String jogoNome) {
        this.jogoNome = jogoNome;
    }

    public String getJogoId() {
        return jogoId;
    }

    public void setJogoId(String jogo) {
        this.jogoId = jogo;
    }

    public Integer getTotalpartidas() {
        return totalpartidas;
    }

    public void setTotalpartidas(Integer totalpartidas) {
        this.totalpartidas = totalpartidas;
    }

    public Integer getPontostotais() {
        return pontostotais;
    }

    public void setPontostotais(Integer pontostotais) {
        this.pontostotais = pontostotais;
    }

    public Integer getTotaldejogadores() {
        return totaldejogadores;
    }

    public void setTotaldejogadores(Integer totaldejogadores) {
        this.totaldejogadores = totaldejogadores;
    }

}
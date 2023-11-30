package model;


import jakarta.persistence.*;

@Entity
@Table(name = "JOGADOR_STATS")
@NamedNativeQuery(name = "JogadorStats.findAll", query = "SELECT js FROM JogadorStats js", resultClass = JogadorStats.class)
public class JogadorStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jogador_id", nullable = false)
    private Integer jogadorId;

    @Column(name = "jogador_username", nullable = false)
    private String jogadorUsername;

    @Column(name = "jogador_email", nullable = false)
    private String jogadorEmail;

    @Column(name = "jogo_id", nullable = false)
    private String jogoId;

    @Column(name = "pontostotais", nullable = false)
    private Integer pontosTotais;

    @Column(name = "partidasjogadas", nullable = false)
    private Integer partidasJogadas;

    @Column(name = "totaldeJogosUnicos", nullable = false)
    private Integer totalDeJogosUnicos;

    // Other attributes and relationships

    public JogadorStats() {
    }
    public Integer getJogadorId() {
        return jogadorId;
    }
    public String getJogadorUsername() {
        return jogadorUsername;
    }
    public String getJogadorEmail() {
        return jogadorEmail;
    }
    public String getJogoId() {
        return jogoId;
    }
    public Integer getPontosTotais() {
        return pontosTotais;
    }
    public Integer getPartidasJogadas() {
        return partidasJogadas;
    }
    public Integer getTotalDeJogosUnicos() {
        return totalDeJogosUnicos;
    }


}


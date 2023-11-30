package model;

import jakarta.persistence.*;

@Entity
@Table(name = "JOGADOR_CRACHA")
@NamedNativeQuery(name = "JogadorCracha.findAll", query = "SELECT jc FROM JOGADOR_CRACHA jc", resultClass = JogadorCracha.class)
public class JogadorCracha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cracha_id", nullable = false)
    private Integer crachaId;

    @Column(name = "jogo_nome", nullable = false)
    private String  jogoNome;
    @Column(name = "cracha_nome", nullable = false)
    private String  crachaNome;
    @Column(name = "jogador_id", nullable = false)
    private Integer jogadorId;


    @Column(name = "jogador_username", nullable = false)
    private String jogadorUsername;

    @Column(name = "jogador_email", nullable = false)
    private String jogadorEmail;

    public JogadorCracha() {
    }

    public Integer getCrachaId() {
        return crachaId;
    }
    public Integer setCrachaId(Integer crachaNameIn) {
        return this.crachaId = crachaNameIn;
    }

    public String getJogoNome() {
        return jogoNome;
    }
    public String setJogoNome(String jogoNomeIn) {
        return this.jogoNome = jogoNomeIn;
    }
    public String getCrachaNome() {
        return crachaNome;
    }
    public String setCrachaNome(String crachaNomeIn) {
        return this.crachaNome = crachaNomeIn;
    }
    public Integer getJogadorId() {
        return jogadorId;
    }
    public Integer setJogadorId(Integer jogadorIdIn) {
        return this.jogadorId = jogadorIdIn;
    }
    public String getJogadorUsername() {
        return jogadorUsername;
    }
    public String setJogadorUsername(String jogadorUsernameIn) {
        return this.jogadorUsername = jogadorUsernameIn;
    }
    public String getJogadorEmail() {
        return jogadorEmail;
    }
    public String setJogadorEmail(String jogadorEmailIn) {
        return this.jogadorEmail = jogadorEmailIn;
    }

}


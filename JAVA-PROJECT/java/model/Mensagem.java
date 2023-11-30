package model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "mensagem")
public class Mensagem {
    @Id
    @Column(name = "id", nullable = false)
    private Short id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversa_id", nullable = false)
    private Conversa conversa;

    @Column(name = "ordem", nullable = false)
    private Short ordem;

    @Lob
    @Column(name = "texto")
    private String texto;

    @Column(name = "horaenvio")
    private Instant horaenvio;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_id", nullable = false)
    private Integer jogador;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_username", nullable = false, referencedColumnName = "username")
    private String jogadorUsername;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_email", nullable = false, referencedColumnName = "email")
    private String jogadorEmail;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public Short getOrdem() {
        return ordem;
    }

    public void setOrdem(Short ordem) {
        this.ordem = ordem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Instant getHoraenvio() {
        return horaenvio;
    }

    public void setHoraenvio(Instant horaenvio) {
        this.horaenvio = horaenvio;
    }

    public Integer getJogador() {
        return jogador;
    }

    public void setJogador(Integer jogador) {
        this.jogador = jogador;
    }

    public String getJogadorUsername() {
        return jogadorUsername;
    }

    public void setJogadorUsername(String jogadorUsername) {
        this.jogadorUsername = jogadorUsername;
    }

    public String getJogadorEmail() {
        return jogadorEmail;
    }

    public void setJogadorEmail(String jogadorEmail) {
        this.jogadorEmail = jogadorEmail;
    }

}
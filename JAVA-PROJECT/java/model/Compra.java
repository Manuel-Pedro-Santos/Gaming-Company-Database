package model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @Column(name = "jogador_id", nullable = false)
    private Integer id;

    //@MapsId
    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_id", nullable = false)
    private Integer jogadoreId;

    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_username", nullable = false, referencedColumnName = "username")
    private String jogadorUsername;

    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_email", nullable = false, referencedColumnName = "email")
    private String jogadorEmail;

    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogo_Id", nullable = false)
    private String jogoId;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJogadoreId() {
        return jogadoreId;
    }

    public void setJogadores(Integer jogadores) {
        this.jogadoreId = jogadores;
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

    public String getJogoId() {
        return jogoId;
    }

    public void setJogoNome(String jogoNome) {
        this.jogoId = jogoNome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
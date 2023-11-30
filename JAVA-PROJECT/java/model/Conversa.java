package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "conversa")
public class Conversa {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "chat_id", nullable = false)
    private Short chatId;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_id", nullable = false)
    private Integer jogador;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @OneToMany(mappedBy = "conversa")
    private Set<Mensagem> mensagems = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getChatId() {
        return chatId;
    }

    public void setChatId(Short chatId) {
        this.chatId = chatId;
    }

    public Integer getJogador() {
        return jogador;
    }

    public void setJogador(Integer jogador) {
        this.jogador = jogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Mensagem> getMensagems() {
        return mensagems;
    }

    public void setMensagems(Set<Mensagem> mensagems) {
        this.mensagems = mensagems;
    }

}
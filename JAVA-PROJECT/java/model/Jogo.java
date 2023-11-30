package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "jogo")
public class Jogo {
    @Id
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "idalfa", length = 10)
    private String idalfa;

    @Column(name = "url")
    private String url;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdalfa() {
        return idalfa;
    }

    public void setIdalfa(String idalfa) {
        this.idalfa = idalfa;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
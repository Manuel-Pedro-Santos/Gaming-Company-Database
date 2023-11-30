package model;
import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="JOGADORES")
@NamedNativeQuery(name="Jogadores.findAll", query="SELECT j FROM JOGADORES j", resultClass = Jogadores.class)
public class Jogadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "partida_numseq")
    private Integer partidaNumseq;


    @Column(name = "regiao_nome", nullable = false)
    private String regiao;

    public Jogadores() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getPartidaNumseq() {
        return partidaNumseq;
    }

    public void setPartidaNumseq(Integer partidaNumseq) {
        this.partidaNumseq = partidaNumseq;
    }

    public String  getRegiao() {
        return regiao;
    }

    public void setRegiao(String  regiao) {
        this.regiao = regiao;
    }
}



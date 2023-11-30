package model;

import jakarta.persistence.*;

@Entity
@Table(name = "partida_multijogador")
public class PartidaMultijogador {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "n_sequencial", nullable = false)
    private Partida nSequencial;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regiao_nome", nullable = false)
    private Regiao regiaoNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

/*
    TODO [JPA Buddy] create field to map the 'estado' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "estado", columnDefinition = "multi_states(0, 0)")
    private Object estado;
*/
}
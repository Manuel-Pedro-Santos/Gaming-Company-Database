package model;

import jakarta.persistence.*;

@Entity
@Table(name = "amizade")
public class Amizade {
    @Id
    @MapsId("jogadorId")
    @JoinColumn(name = "jogador_id", nullable = false)
    private Integer jogador;

    @MapsId("amigoId")
    @JoinColumn(name = "amigo_id", nullable = false)
    private Integer amigo;

    public Integer getId() {
        return jogador;
    }

    public void setId(Integer id) {
        this.jogador = id;
    }

    public Integer getAmigo() {
        return amigo;
    }

    public void setAmigo(Integer amigo) {
        this.amigo = amigo;
    }

}
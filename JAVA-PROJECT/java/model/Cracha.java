package model;

import jakarta.persistence.*;
import org.eclipse.persistence.annotations.OptimisticLocking;
import org.eclipse.persistence.annotations.OptimisticLockingType;

@Entity
@Table(name = "CRACHA")
@OptimisticLocking(type= OptimisticLockingType.CHANGED_COLUMNS)
@NamedNativeQuery(name = "CRACHA.findAll", query = "SELECT c FROM CRACHA c", resultClass = Cracha.class)

public class Cracha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cracha_id", nullable = false)
    private Integer crachaID;

    @Column(name = "nome", nullable = false)
    private String nomeCracha;
    @Column(name = "jogo_id", nullable = false)
    private String jogoId;

    @Column(name = "limitepontos", nullable = false)
    private Integer limitePontos;


    public Cracha() {
    }
    public Integer getCrachaID() {
        return crachaID;
    }
    public String getNomeCracha() {
        return nomeCracha;
    }
    public String getJogoNome() {
        return jogoId;
    }

    public Integer getLimitePontos() {
        return limitePontos;
    }
    public Integer setLimitePontos(Integer limitePontos) {
        return this.limitePontos = limitePontos;
    }


}


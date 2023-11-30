package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "partidas")
public class Partida {
    @Id
    @Column(name = "n_sequencial", nullable = false)
    private Short id;

    @Column(name = "dtinicio")
    private LocalDate dtinicio;

    @Column(name = "dtfinal")
    private LocalDate dtfinal;

    @Column(name = "horainicio")
    private LocalTime horainicio;

    @Column(name = "horafim")
    private LocalTime horafim;

    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiao_nome")
    private Regiao regiaoNome;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public LocalDate getDtinicio() {
        return dtinicio;
    }

    public void setDtinicio(LocalDate dtinicio) {
        this.dtinicio = dtinicio;
    }

    public LocalDate getDtfinal() {
        return dtfinal;
    }

    public void setDtfinal(LocalDate dtfinal) {
        this.dtfinal = dtfinal;
    }

    public LocalTime getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(LocalTime horainicio) {
        this.horainicio = horainicio;
    }

    public LocalTime getHorafim() {
        return horafim;
    }

    public void setHorafim(LocalTime horafim) {
        this.horafim = horafim;
    }

    public Regiao getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(Regiao regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

}
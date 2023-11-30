package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
@NamedNativeQuery(name = "Funcionario.findAll",query = "SElect f from Funcionario f", resultClass = Funcionario.class)
public class Funcionario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer numFunc;

    @Column(name = "nome")
    private String nomeFunc;

    @Column(name = "idade")
    private Integer idadeFunc;

    public Funcionario(){}

    public Integer getnumFunc() {return numFunc;}
    public String getnomeFunc() { return nomeFunc;}
    public Integer getidadeFunc() {return idadeFunc;}

    public String setTarefId(String newStr){ return this.nomeFunc = newStr; }
    public Integer setTarefId(Integer newInt){ return this.idadeFunc = newInt;}
}
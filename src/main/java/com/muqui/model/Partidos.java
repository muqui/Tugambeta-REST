package com.muqui.model;
// Generated 5/11/2016 06:03:56 PM by Hibernate Tools 4.3.1

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Partidos generated by hbm2java
 */
@Entity
@Table(name = "partidos")
public class Partidos implements java.io.Serializable {

    private Integer idpartidos;
    private Quiniela quiniela;
    private String resultado;
    private String local;
    private String visitante;
    private Integer goleslocal;
    private int golesvisita;
    private Set<Jugadorresultados> jugadorresultadoses = new HashSet<Jugadorresultados>(0);

    public Partidos() {
    }

    public Partidos(String local, String visita) {
        this.local = local;
        this.visitante = visita;
    }

    public Partidos(Quiniela quiniela, int golesvisita) {
        this.quiniela = quiniela;
        this.golesvisita = golesvisita;
    }

    public Partidos(Quiniela quiniela, String resultado, String local, String visitante, Integer goleslocal, int golesvisita, Set<Jugadorresultados> jugadorresultadoses) {
        this.quiniela = quiniela;
        this.resultado = resultado;
        this.local = local;
        this.visitante = visitante;
        this.goleslocal = goleslocal;
        this.golesvisita = golesvisita;
        this.jugadorresultadoses = jugadorresultadoses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idpartidos", unique = true, nullable = false)
    public Integer getIdpartidos() {
        return this.idpartidos;
    }

    public void setIdpartidos(Integer idpartidos) {
        this.idpartidos = idpartidos;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiniela_idquiniela", nullable = false)
    public Quiniela getQuiniela() {
        return this.quiniela;
    }

    public void setQuiniela(Quiniela quiniela) {
        this.quiniela = quiniela;
    }

    @Column(name = "resultado", length = 1)
    public String getResultado() {
        return this.resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Column(name = "local", length = 45)
    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Column(name = "visitante", length = 45)
    public String getVisitante() {
        return this.visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    @Column(name = "goleslocal")
    public Integer getGoleslocal() {
        return this.goleslocal;
    }

    public void setGoleslocal(Integer goleslocal) {
        this.goleslocal = goleslocal;
    }

    @Column(name = "golesvisita", nullable = false)
    public int getGolesvisita() {
        return this.golesvisita;
    }

    public void setGolesvisita(int golesvisita) {
        this.golesvisita = golesvisita;
    }

     @OneToMany(fetch = FetchType.EAGER, mappedBy = "partidos")
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "partidos") se cambio  de lazy a eager REST
    @JsonIgnore         //cambios REST
    public Set<Jugadorresultados> getJugadorresultadoses() {
        return this.jugadorresultadoses;
    }

    public void setJugadorresultadoses(Set<Jugadorresultados> jugadorresultadoses) {
        this.jugadorresultadoses = jugadorresultadoses;
    }

}

package com.muqui.model;
// Generated 5/11/2016 06:03:56 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.UniqueConstraint;

/**
 * Pagina generated by hbm2java
 */
@Entity
@Table(name = "pagina", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Pagina implements java.io.Serializable {

    

    private Integer id;
    private Users users;
    private String nombre;
    private Integer actual;
    private String tipo;
    private String dobles;
    private String triples;

    private Set<Liga> ligas = new HashSet<Liga>(0);

    public Pagina() {
    }

    public Pagina(Users users) {
        this.users = users;
    }


    public Pagina(Users users, String nombre, Integer actual, String tipo, Set<Liga> ligas , String dobles, String triples) {
        this.users = users;
        this.nombre = nombre;
        this.actual = actual;
        this.tipo = tipo;
        this.ligas = ligas;
        this.dobles = dobles;
        this.triples = triples;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_username", nullable = false)
    @JsonBackReference
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Column(name = "nombre", unique = true, length = 45)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "tipo", length = 10)
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = "actual")
    public Integer getActual() {
        return this.actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pagina")
    public Set<Liga> getLigas() {
        return this.ligas;
    }

    public void setLigas(Set<Liga> ligas) {
        this.ligas = ligas;
    }
    /**
     * @return the dobles
     */
      @Column(name = "dobles", length = 5)
    public String getDobles() {
        return dobles;
    }

    /**
     * @param dobles the dobles to set
     */
    public void setDobles(String dobles) {
        this.dobles = dobles;
    }

    /**
     * @return the triples
     */
      @Column(name = "triples", length = 5)
    public String getTriples() {
        return triples;
    }

    /**
     * @param triples the triples to set
     */
    public void setTriples(String triples) {
        this.triples = triples;
    }

}
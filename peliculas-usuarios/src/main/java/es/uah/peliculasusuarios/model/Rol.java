package es.uah.peliculasusuarios.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol")
    private Integer idRol;
    @Basic
    @Column(name = "authority")
    private String authority;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol that = (Rol) o;
        return Objects.equals(idRol, that.idRol) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, authority);
    }
}

package es.uah.peliculasusuarios.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "criticas", schema = "peliculasusuarios")
public class Critica {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCritica")
    private Integer idCritica;
    @Basic
    @Column(name = "idPelicula")
    private Integer idPelicula;
    @Basic
    @Column(name = "valoracion")
    private String valoracion;
    @Basic
    @Column(name = "nota")
    private Integer nota;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "Usuarios_idUsuario")
    private Integer usuariosIdUsuario;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @JsonIgnoreProperties("criticas")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdCritica() {
        return idCritica;
    }

    public void setIdCritica(Integer idCritica) {
        this.idCritica = idCritica;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Integer usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Critica critica = (Critica) o;
        return Objects.equals(idCritica, critica.idCritica) && Objects.equals(idPelicula, critica.idPelicula) && Objects.equals(valoracion, critica.valoracion) && Objects.equals(nota, critica.nota) && Objects.equals(fecha, critica.fecha) && Objects.equals(usuariosIdUsuario, critica.usuariosIdUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCritica, idPelicula, valoracion, nota, fecha, usuariosIdUsuario);
    }
}

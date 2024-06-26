package es.uah.peliculasfront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

public class Critica {

    @JsonProperty("id")
    private Integer idCritica;
    private Integer idPelicula;
    private String valoracion;
    private Integer nota;
    private LocalDate fecha;
    private Usuario usuario;

    public Critica(Integer idCritica, Integer idPelicula, String valoracion, Integer nota, LocalDate fecha, Usuario usuario) {
        this.idCritica = idCritica;
        this.idPelicula = idPelicula;
        this.valoracion = valoracion;
        this.nota = nota;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Critica() {

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

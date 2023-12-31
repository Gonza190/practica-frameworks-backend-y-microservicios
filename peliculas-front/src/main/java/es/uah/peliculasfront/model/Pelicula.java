package es.uah.peliculasfront.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class Pelicula {

    private Integer id;
    private String titulo;
    private Integer anno;
    private Integer duracion;
    private String pais;
    private String direccion;
    private String genero;
    private String sinopsis;
    private String portada;
    private List<Actor> actores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public void addActor(Actor actor) {
        if (actor != null) {
            getActores().add(actor);
        }
    }

    public void removeActor(Actor actor) {
        if (actor != null) {
            getActores().remove(actor);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id) && Objects.equals(titulo, pelicula.titulo) && Objects.equals(anno, pelicula.anno) && Objects.equals(duracion, pelicula.duracion) && Objects.equals(pais, pelicula.pais) && Objects.equals(direccion, pelicula.direccion) && Objects.equals(genero, pelicula.genero) && Objects.equals(sinopsis, pelicula.sinopsis) && Objects.equals(portada, pelicula.portada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, anno, duracion, pais, direccion, genero, sinopsis, portada);
    }
}

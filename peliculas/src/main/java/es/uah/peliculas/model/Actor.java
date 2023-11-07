package es.uah.peliculas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Basic
    @Column(name = "pais")
    private String pais;

    //Relacion @ManyToMany hecha a mano
    @ManyToMany
    @JoinTable(name="peliculasActores", joinColumns = {
            @JoinColumn(name = "pelicula_id", referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name = "actor_id", referencedColumnName = "id")
    })
    @JsonIgnoreProperties("actores")
    private List<Pelicula> peliculas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void addPelicula(Pelicula pelicula){
        if(pelicula!=null){
            getPeliculas().add(pelicula);
            pelicula.addActor(this);
        }
    }

    public void removePelicula(Pelicula pelicula){
        if(pelicula!= null){
            pelicula.removeActor(this);
            getPeliculas().remove(pelicula);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id) && Objects.equals(nombre, actor.nombre) && Objects.equals(fechaNacimiento, actor.fechaNacimiento) && Objects.equals(pais, actor.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaNacimiento, pais);
    }
}

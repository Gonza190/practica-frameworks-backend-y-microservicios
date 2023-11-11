package es.uah.peliculas.dao;

import es.uah.peliculas.model.Actor;
import es.uah.peliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculasDAOImp implements IPeliculasDAO{

    @Autowired
    IPeliculasJPA peliculasJPA;
    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasJPA.findAll();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        Optional<Pelicula> optional = peliculasJPA.findById(idPelicula);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        return peliculasJPA.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        return peliculasJPA.findByGeneroContainingIgnoreCase(genero);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorActor(Actor actor) {
        return peliculasJPA.findByActores(actor);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) {

        Optional<Pelicula> optional = peliculasJPA.findById(idPelicula);
        if(optional.isPresent()){
            Pelicula pelicula = optional.get();
            List<Actor> actores = pelicula.getActores();
            for(Actor actor: actores){
                actores.remove(pelicula);
            }

            peliculasJPA.deleteById(idPelicula);
        }


    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }
}


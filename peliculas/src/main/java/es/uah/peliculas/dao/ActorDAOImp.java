package es.uah.peliculas.dao;

import es.uah.peliculas.model.Actor;
import es.uah.peliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDAOImp implements IActoresDAO{

    @Autowired
    IActoresJPA actoresJPA;
    @Autowired
    IPeliculasJPA peliculasJPA;

    @Override
    public List<Actor> buscarTodos() {
        return actoresJPA.findAll();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Optional<Actor> optional = actoresJPA.findById(idActor);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public Actor buscarActorPorNombre(String nombre) {
        Optional<Actor> optional = Optional.ofNullable(actoresJPA.findByNombre(nombre));
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    public void eliminarActor(Integer idActor){
        Optional<Actor> optional = actoresJPA.findById(idActor);
        if(optional.isPresent()){
            Actor actor = optional.get();
            List<Pelicula> peliculas = actor.getPeliculas();
            for (Pelicula pelicula: peliculas){
                peliculas.remove(actor);
            }
            actoresJPA.deleteById(actor.getId());
        }
    }
    @Override
    public void actualizarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    @Override
    public void anadirActorAPelicula(Integer idActor, Integer idPelicula) {
        Optional<Actor> optionalActor = actoresJPA.findById(idActor);
        if(optionalActor.isPresent()){
            Actor actor = optionalActor.get();
            Optional<Pelicula> optionalPelicula = peliculasJPA.findById(idPelicula);
            if(optionalActor.isPresent()){
                actor.addPelicula(optionalPelicula.get());
                actoresJPA.save(actor);
            }
        }
    }
}

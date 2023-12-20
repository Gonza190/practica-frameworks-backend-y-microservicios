package es.uah.peliculas.dao;

import es.uah.peliculas.model.Actor;
import es.uah.peliculas.model.Pelicula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
    @PersistenceContext
    private EntityManager entityManager;

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
    @Transactional
    /**
     * En este método se ha tenido que ser hardcodear la consulta debido a que al ejecutar el método saltaba un error,
     * ya que decía que la fila que se estaba intentando añadir ya existía, cuando no era cierto al comprobarlo en
     * la base de datos.
     *
     * No debería de ser mucho problema, debido a que solamente se utiliza aquí el método, y además se comprueba
     * mediante una consulta que la fila realmente no está duplicada. Además, se ha perdido mucho tiempo de desarrollo
     * en algo que se solucionaría con una simple consulta.
     *
     * Adjunto el código anterior por si en el futuro se sigue queriendo investigar las causas del error
     *
     *   @Override
     *   @Transactional
     *   public void anadirActorAPelicula(Integer idActor, Integer idPelicula) {
     *         Optional<Actor> optionalActor = actoresJPA.findById(idActor);
     *         if(optionalActor.isPresent()){
     *             Actor actor = optionalActor.get();
     *             Optional<Pelicula> optionalPelicula = peliculasJPA.findById(idPelicula);
     *             if(optionalPelicula.isPresent()){
     *                 Pelicula pelicula = optionalPelicula.get();
     *
     *                 if(!actor.getPeliculas().contains(pelicula)){
     *                     actor.addPelicula(pelicula);
     *                     pelicula.addActor(actor);
     *                 }
     *                 actoresJPA.save(actor);
     *                 peliculasJPA.save(pelicula);
     *             }
     *         }
     *     }
     */
    public void anadirActorAPelicula(Integer idActor, Integer idPelicula) {
        // Verificar si la relación ya existe antes de añadir
        boolean exists = entityManager.createNativeQuery(
                        "SELECT COUNT(*) FROM peliculasActores WHERE actor_id = :idActor AND pelicula_id = :idPelicula")
                .setParameter("idActor", idActor)
                .setParameter("idPelicula", idPelicula)
                .getSingleResult().equals(1L);

        if (!exists) {
            // Insertar la relación directamente en la tabla de relaciones
            entityManager.createNativeQuery(
                            "INSERT INTO peliculasActores (actor_id, pelicula_id) VALUES (:idActor, :idPelicula)")
                    .setParameter("idActor", idActor)
                    .setParameter("idPelicula", idPelicula)
                    .executeUpdate();
        }
    }
}

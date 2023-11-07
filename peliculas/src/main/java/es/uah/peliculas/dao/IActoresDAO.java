package es.uah.peliculas.dao;

import es.uah.peliculas.model.Actor;

import java.util.List;
import java.util.Map;

public interface IActoresDAO{

    List<Actor> buscarTodos();
    Actor buscarActorPorId(Integer idActor);
    Actor buscarActorPorNombre(String nombre);
    void guardarActor(Actor actor);
    void actualizarActor(Actor actor);
    void anadirActorAPelicula(Integer idActor, Integer idPelicula);



}

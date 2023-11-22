package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IActoresService {

    Page<Actor> buscarTodos(Pageable pageable);
    List<Actor> buscarTodos();
    Actor buscarActorPorId(Integer id);
    Page<Actor> buscarActorPorNombre(Pageable pageable);
    Page<Actor> buscarActorPorPelicula(Pageable pageable);
    void guardarActor(Actor Actor);
    void eliminarActor(Integer id);


}

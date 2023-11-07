package es.uah.peliculas.dao;

import es.uah.peliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActoresJPA extends JpaRepository<Actor, Integer> {

    Actor findByNombre(String nombre);

}

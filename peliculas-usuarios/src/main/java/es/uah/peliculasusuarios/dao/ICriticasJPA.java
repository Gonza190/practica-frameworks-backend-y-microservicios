package es.uah.peliculasusuarios.dao;

import es.uah.peliculasusuarios.model.Critica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICriticasJPA extends JpaRepository<Critica, Integer> {

    List<Critica> findByIdPelicula(int idPelicula);

}

package es.uah.peliculasusuarios.service;

import es.uah.peliculasusuarios.model.Critica;

import java.util.List;

public interface ICriticasService {

    List<Critica> buscarTodas();

    List<Critica> buscarCriticasPorIdPelicula(Integer idPelicula);

    Critica buscarCriticaPorId(Integer idCritica);

    void guardarCritica(Critica critica);

    void eliminarCritica(Integer idCritica);


}

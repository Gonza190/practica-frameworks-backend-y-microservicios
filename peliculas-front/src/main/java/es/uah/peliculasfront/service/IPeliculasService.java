package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPeliculasService {

    Page<Pelicula> buscarTodas(Pageable pageable);
    Pelicula buscarPeliculaPorId(Integer id);
    Page<Pelicula> buscarPeliculasPorTitulo(Pageable pageable);
    Page<Pelicula> buscarPeliculasPorGenero(Pageable pageable);
    Page<Pelicula> buscarPeliculasPorActor(Pageable pageable);
    void guardarPelicula(Pelicula pelicula);
    void eliminarPelicula(Integer id);


}

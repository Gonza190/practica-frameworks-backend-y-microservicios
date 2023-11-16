package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPeliculasService {

    Page<Pelicula> buscarTodas(Pageable pageable);
    Pelicula buscarPeliculaPorId(Integer id);
    //busqueda con paginacion
    Page<Pelicula> buscarPeliculasPorTitulo(Pageable pageable);
    Page<Pelicula> buscarPeliculasPorGenero(Pageable pageable);
    Page<Pelicula> buscarPeliculasPorActor(Pageable pageable);
    //busqueda sin paginacion
    List<Pelicula> buscarPeliculasPorTitulo(String titulo);
    List<Pelicula> buscarPeliculasPorGenero(String genero);
    List<Pelicula> buscarPeliculasPorActor(String actor);
    void guardarPelicula(Pelicula pelicula);
    void eliminarPelicula(Integer id);


}

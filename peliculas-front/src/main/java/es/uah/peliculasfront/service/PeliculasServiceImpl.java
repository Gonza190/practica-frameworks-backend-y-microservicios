package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PeliculasServiceImpl implements IPeliculasService{

    @Autowired
    RestTemplate template;
    String URL_BASE = "http://localhost:8001/peliculas";

    @Override
    public Page<Pelicula> buscarTodas(Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(URL_BASE, Pelicula[].class);
        List<Pelicula> peliculasList = Arrays.asList(peliculas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Pelicula> list;

        if(peliculasList.size()<startItem){
            list = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem+pageSize, peliculasList.size());
            list = peliculasList.subList(startItem, toIndex);
        }

        Page<Pelicula> page = new PageImpl<>(list, PageRequest.of(currentPage,pageSize), peliculasList.size());
        return page;

    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer id) {

        Pelicula pelicula = template.getForObject(URL_BASE + "/" +id, Pelicula.class);
        return pelicula;
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorTitulo(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorGenero(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorActor(Pageable pageable) {
        return null;
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {

    }

    @Override
    public void eliminarPelicula(Integer id) {

    }
}

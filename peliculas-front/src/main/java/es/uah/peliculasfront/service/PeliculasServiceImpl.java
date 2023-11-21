package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.*;

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
        //System.out.println(pelicula.getPortada());
        //String decoded = new String(Base64.getDecoder().decode(pelicula.getPortada()));
        //System.out.println("-----------------------");
        //System.out.println(decoded);
        return pelicula;
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorTitulo(String titulo, Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(URL_BASE + "/titulo/" + titulo, Pelicula[].class);
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
    public Page<Pelicula> buscarPeliculasPorGenero(String genero, Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(URL_BASE + "/genero/" + genero, Pelicula[].class);
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
    public Page<Pelicula> buscarPeliculasPorActor(String actor, Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(URL_BASE + "/actor/" + actor, Pelicula[].class);
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
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        Pelicula[] peliculas = template.getForObject(URL_BASE + "/titulo/" + titulo, Pelicula[].class);
        return Arrays.asList(peliculas);

    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        Pelicula[] peliculas = template.getForObject(URL_BASE + "/genero/" + genero, Pelicula[].class);
        return Arrays.asList(peliculas);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorActor(String actor) {
        Pelicula[] peliculas = template.getForObject(URL_BASE, Pelicula[].class);
        return Arrays.asList(peliculas);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {

        if(pelicula.getId() != null && pelicula.getId()>0){
            template.put(URL_BASE, pelicula);
        }else {
            pelicula.setId(0);
            template.postForObject(URL_BASE, pelicula, String.class);
        }
    }

    @Override
    public void eliminarPelicula(Integer id) {
        template.delete(URL_BASE + "/" + id);
    }
}

package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Actor;
import es.uah.peliculasfront.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ActoresServiceImpl implements IActoresService {

    @Autowired
    RestTemplate template;
    String URL_BASE = "http://localhost:8001/actores";


    @Override
    public List<Actor> buscarTodos() {
        Actor[] actores = template.getForObject(URL_BASE, Actor[].class);
        return Arrays.asList(actores);
    }

    @Override
    public Page<Actor> buscarTodos(Pageable pageable) {

        Actor[] actores = template.getForObject(URL_BASE, Actor[].class);
        List<Actor> actoresList = Arrays.asList(actores);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Actor> list;

        if (actoresList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, actoresList.size());
            list = actoresList.subList(startItem, toIndex);
        }

        Page<Actor> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), actoresList.size());
        return page;

    }

    @Override
    public Actor buscarActorPorId(Integer id) {
        Actor actor = template.getForObject(URL_BASE + "/" + id, Actor.class);
        return actor;
    }

    @Override
    public Page<Actor> buscarActorPorNombre(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Actor> buscarActorPorPelicula(Pageable pageable) {
        return null;
    }

    @Override
    public void guardarActor(Actor actor) {
        if (actor.getId() != null && actor.getId() > 0) {
            template.put(URL_BASE, actor);
        } else {
            actor.setId(0);
            template.postForObject(URL_BASE, actor, String.class);
        }
    }

    @Override
    public void eliminarActor(Integer id) {

        template.delete(URL_BASE + "/" + id);
    }
}

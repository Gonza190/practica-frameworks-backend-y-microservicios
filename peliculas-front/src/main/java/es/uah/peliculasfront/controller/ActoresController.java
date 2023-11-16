package es.uah.peliculasfront.controller;

import es.uah.peliculasfront.model.Actor;
import es.uah.peliculasfront.model.Pelicula;
import es.uah.peliculasfront.paginator.PageRender;
import es.uah.peliculasfront.service.IActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/actores")
public class ActoresController {

    @Autowired
    IActoresService actoresService;

    @GetMapping("")
    public String listadoActores(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 12);
        Page<Actor> actores = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/actores", actores);
        model.addAttribute("actores", actores);
        model.addAttribute("page", pageRender);
        return "views/listadoActores";
    }

    @GetMapping("/alta")
    public String altaActor(Model model){
        model.addAttribute("nombre", "Nuevo actor");
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "views/formActor";
    }

    @GetMapping("/gestion")
    public String gestionActores(Model model, @RequestParam(name = "page", defaultValue = "0") int page){

        Pageable pageable = PageRequest.of(page,10);
        Page<Actor> actores = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/actores/gestion",actores);
        model.addAttribute("actores",actores);
        model.addAttribute("page", pageRender);

        return "views/gestionActores";
    }


    @GetMapping("/{id}")
    public String infoActor(Model model, @PathVariable(name = "id") int id){

        Actor actor = actoresService.buscarActorPorId(id);
        model.addAttribute("actor", actor);
        String peliculas = formatearPeliculas(actor);
        model.addAttribute("peliculas", peliculas);
        return "views/infoActor";

    }

    @GetMapping("/modificar/{id}")
    public String modificarActor(Model model, @PathVariable(name = "id") int id){

        Actor actor = actoresService.buscarActorPorId(id);
        model.addAttribute("actor", actor);
        return "views/modActor";

    }

    private String formatearPeliculas(Actor actor){
        String formateado = "";
        for (int i=0;i<actor.getPeliculas().size()-1;i++){
            formateado+=actor.getPeliculas().get(i).getTitulo() + ", ";
        }
        formateado+=actor.getPeliculas().get(actor.getPeliculas().size()-1).getTitulo();
        return formateado;
    }



}

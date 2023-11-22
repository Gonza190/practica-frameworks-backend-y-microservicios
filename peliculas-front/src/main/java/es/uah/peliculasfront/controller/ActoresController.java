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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/actores")
public class ActoresController {

    @Autowired
    IActoresService actoresService;

    @GetMapping("")
    public String listadoActores(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 12);
        Page<Actor> actores = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/actores", actores);
        model.addAttribute("actores", actores);
        model.addAttribute("page", pageRender);
        return "views/listadoActores";
    }

    @GetMapping("/alta")
    public String altaActor(Model model) {
        model.addAttribute("nombre", "Nuevo actor");
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "views/formActor";
    }

    @GetMapping("/gestion")
    public String gestionActores(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Actor> actores = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/actores/gestion", actores);
        model.addAttribute("actores", actores);
        model.addAttribute("page", pageRender);

        return "views/gestionActores";
    }


    @GetMapping("/{id}")
    public String infoActor(Model model, @PathVariable(name = "id") int id) {

        Actor actor = actoresService.buscarActorPorId(id);
        model.addAttribute("actor", actor);
        String peliculas = formatearPeliculas(actor);
        model.addAttribute("peliculas", peliculas);
        return "views/infoActor";

    }

    @GetMapping("/modificar/{id}")
    public String modificarActor(Model model, @PathVariable(name = "id") int id) {

        Actor actorGuardado = actoresService.buscarActorPorId(id);
        Actor nuevoActor = actoresService.buscarActorPorId(id);
        model.addAttribute("actorGuardado", actorGuardado);
        model.addAttribute("nuevoActor", nuevoActor);
        return "views/modActor";

    }

    @PostMapping("/guardar/")
    public String guardarActor(Model model,
                               Actor nuevoActor,
                               RedirectAttributes atributos) {

        actoresService.guardarActor(nuevoActor);
        atributos.addFlashAttribute("msg", "Los datos del actor han sido guardados");
        return "redirect:/actores/gestion";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model,
                                @PathVariable("id") Integer id,
                                RedirectAttributes atributos) {

        actoresService.eliminarActor(id);
        atributos.addFlashAttribute("msg", "Los datos del actor fueron borrados");

        return "redirect:/actores";
    }

    private String formatearPeliculas(Actor actor) {
        String formateado = "";
        if (actor.getPeliculas().size() > 0) {
            for (int i = 0; i < actor.getPeliculas().size() - 1; i++) {
                formateado += actor.getPeliculas().get(i).getTitulo() + ", ";
            }
            formateado += actor.getPeliculas().get(actor.getPeliculas().size() - 1).getTitulo();
        }
        return formateado;
    }


}

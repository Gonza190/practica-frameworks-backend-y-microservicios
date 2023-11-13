package es.uah.peliculasfront.controller;

import es.uah.peliculasfront.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = {"", "/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/pelicula/alta")
    public String altaPelicula(Model model){
        model.addAttribute("titulo", "Nueva película");
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        return "peliculas/formPelicula";
    }

    @GetMapping("/actor/alta")
    public String altaActor(Model model){
        model.addAttribute("nombre", "Nuevo actor");
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "peliculas/formActor";
    }
}

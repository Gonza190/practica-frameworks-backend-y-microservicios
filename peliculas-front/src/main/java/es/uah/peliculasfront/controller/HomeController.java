package es.uah.peliculasfront.controller;

import es.uah.peliculasfront.model.*;
import es.uah.peliculasfront.service.IActoresService;
import es.uah.peliculasfront.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    IPeliculasService peliculasService;
    @Autowired
    IActoresService actoresService;

    @GetMapping(value = {"", "/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/asociar")
    public String asociar(Model model) {

        List<Pelicula> peliculas = peliculasService.buscarTodas();
        List<Actor> actores = actoresService.buscarTodos();

        model.addAttribute("peliculas",peliculas);
        model.addAttribute("actores", actores);

        return "views/asociar";
    }

}

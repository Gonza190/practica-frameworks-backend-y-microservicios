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
    public String home() {
        return "home";
    }

    @GetMapping("/busqueda")
    public String listadoBusqueda(){
        return "views/listado";
    }

}

package es.uah.peliculasfront.controller;

import es.uah.peliculasfront.model.Pelicula;
import es.uah.peliculasfront.paginator.PageRender;
import es.uah.peliculasfront.service.IPeliculasService;
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
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    IPeliculasService peliculasService;

    @GetMapping("/")
    public String listadoPeliculas(Model model){
        return "views/listadoPeliculas";
    }

    @GetMapping("/alta")
    public String altaPelicula(Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        return "views/formPelicula";
    }



    @GetMapping("/gestion")
    public String gestionPeliculas(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page,5);
        Page<Pelicula> peliculas = peliculasService.buscarTodas(pageable);
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/peliculas/gestion", peliculas);
        model.addAttribute("peliculas",peliculas);
        model.addAttribute("page", pageRender);
        return "views/gestionPeliculas";
    }


    @GetMapping("/{id}")
    public String infoPelicula(Model model, @PathVariable(name = "id") int id){

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        model.addAttribute("pelicula", pelicula);
        String actores = formatearActores(pelicula);
        model.addAttribute("actores", actores);
        return "views/infoPelicula";

    }

    @GetMapping("/modificar/{id}")
    public String modificarPelicula(Model model, @PathVariable(name = "id") int id){

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        model.addAttribute("pelicula", pelicula);
        return "views/modPelicula";

    }

    private String formatearActores(Pelicula pelicula){
        String formateado = "";
        for (int i=0;i<pelicula.getActores().size()-1;i++){
            formateado+=pelicula.getActores().get(i).getNombre() + ", ";
        }
        formateado+=pelicula.getActores().get(pelicula.getActores().size()-1).getNombre();
        return formateado;
    }



}

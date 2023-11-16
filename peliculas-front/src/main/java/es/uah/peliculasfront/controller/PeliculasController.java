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

import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    IPeliculasService peliculasService;

    @GetMapping("/")
    public String listadoPeliculas(Model model) {
        return "views/listadoPeliculas";
    }

    @GetMapping("/alta")
    public String altaPelicula(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        return "views/formPelicula";
    }


    @GetMapping("/gestion")
    public String gestionPeliculas(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = peliculasService.buscarTodas(pageable);
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/peliculas/gestion", peliculas);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRender);
        return "views/gestionPeliculas";
    }


    @GetMapping("/{id}")
    public String infoPelicula(Model model, @PathVariable(name = "id") int id) {

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        model.addAttribute("pelicula", pelicula);
        String actores = formatearActores(pelicula);
        model.addAttribute("actores", actores);
        return "views/infoPelicula";

    }

    @GetMapping("/modificar/{id}")
    public String modificarPelicula(Model model, @PathVariable(name = "id") int id) {

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        model.addAttribute("pelicula", pelicula);
        return "views/modPelicula";

    }

    /*
     * buscarPor: string por el que buscar en la BBDD
     * tipo: hay tres tipos de búsqueda
     *   tipo=0: buscar por título de la pelicula
     *   tipo=1: buscar por género de la pelicula
     *   tipo=2: buscar por actor que aparece en la película
     */
    @GetMapping("/buscar")
    public String buscarPelicula(Model model,
                                 @RequestParam(name = "buscarPor") String buscarPor,
                                 @RequestParam(name = "tipo") int tipo) {

        List<Pelicula> peliculas;
        switch (tipo) {
            case 0: //búsqueda por título
                peliculas=peliculasService.buscarPeliculasPorTitulo(buscarPor);
                model.addAttribute("tipo","título");
                break;
            case 1: //búsqueda por género
                peliculas=peliculasService.buscarPeliculasPorGenero(buscarPor);
                model.addAttribute("tipo","género");
                break;
            case 2: //búsqueda por actor
                peliculas=peliculasService.buscarPeliculasPorActor(buscarPor);
                model.addAttribute("tipo","actor");
                break;
            default:
                peliculas = null;
        }
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("busqueda", buscarPor);
        return "views/busquedaPeliculas";
    }


    private String formatearActores(Pelicula pelicula) {
        String formateado = "";
        for (int i = 0; i < pelicula.getActores().size() - 1; i++) {
            formateado += pelicula.getActores().get(i).getNombre() + ", ";
        }
        formateado += pelicula.getActores().get(pelicula.getActores().size() - 1).getNombre();
        return formateado;
    }


}

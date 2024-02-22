package es.uah.peliculasfront.controller;

import es.uah.peliculasfront.model.Critica;
import es.uah.peliculasfront.model.Pelicula;
import es.uah.peliculasfront.model.Usuario;
import es.uah.peliculasfront.paginator.PageRender;
import es.uah.peliculasfront.service.ICriticasService;
import es.uah.peliculasfront.service.IPeliculasService;
import es.uah.peliculasfront.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/criticas")
public class CriticasController {
    @Autowired
    ICriticasService criticasService;

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    IPeliculasService peliculasService;

    @GetMapping("/listado")
    public String listadoCriticas(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Critica> listado = criticasService.buscarTodas(pageable);
        PageRender<Critica> pageRender = new PageRender<Critica>("/criticas/listado", listado);
        model.addAttribute("titulo", "Listado de todas las críticas");
        model.addAttribute("listadoCriticas", listado);
        model.addAttribute("page", pageRender);
        return "views/usuarios/listCritica";
    }


    @GetMapping("/{idPelicula}")
    public String criticasDeUnaPelicula(Model model,
                                        @RequestParam(name="page", defaultValue="0") int page,
                                        @PathVariable("idPelicula") Integer idPelicula ){


        Pageable pageable = PageRequest.of(page, 5);
        Page<Critica> listado = criticasService.buscarCriticasPorIdPelicula(idPelicula,pageable);
        PageRender<Critica> pageRender = new PageRender<Critica>("/criticas/{idPelicula}", listado);
        Pelicula pelicula = peliculasService.buscarPeliculaPorId(idPelicula);
        model.addAttribute("película", pelicula);
        model.addAttribute("titulo", "Críticas de la película: " + pelicula.getTitulo());
        model.addAttribute("listadoCriticas", listado);
        model.addAttribute("page", pageRender);
        return "views/usuarios/criticasPeli";

    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Critica critica = new Critica();
        model.addAttribute("titulo", "Nueva critica");
        model.addAttribute("critica", critica);
        return "views/usuarios/formCritica";
    }

    @PostMapping("/guardar/")
    public String guardarCritica(Model model, Critica critica, RedirectAttributes attributes) {
        String resultado = criticasService.guardarCritica(critica);
        model.addAttribute("titulo", "Nueva critica");
        attributes.addFlashAttribute("msg", resultado);
        return "redirect:/matriculas/listado";
    }

    @GetMapping("/criticar/{idPelicula}")
    public String matricular(@PathVariable("idPelicula") Integer idPelicula, RedirectAttributes attributes, Principal principal) {
        /** TERMINAR MÉTODO
        Usuario usuario = usuariosService.buscarUsuarioPorCorreo(principal.getName());
        Critica critica = new Critica(idPelicula, usuario);
        String resultado = criticasService.guardarCritica(critica);
        attributes.addFlashAttribute("msg", resultado);
         */
        return "redirect:/peliculas";
    }

    @GetMapping("/editar/{id}")
    public String editarCritica(Model model, @PathVariable("id") Integer id) {

        Critica critica = criticasService.buscarCriticaPorId(id);
        model.addAttribute("titulo", "Editar critica");
        model.addAttribute("critica", critica);
        return "usuarios/formCritica";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarCritica(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Critica critica = criticasService.buscarCriticaPorId(id);
        if (critica != null) {
            criticasService.eliminarCritica(id);
            attributes.addFlashAttribute("msg", "Los datos de la crítica fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Crítica no encontrada!");
        }

        return "redirect:/criticas/listado";
    }

}

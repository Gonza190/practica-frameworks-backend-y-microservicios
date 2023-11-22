package es.uah.peliculasfront.controller;

import es.uah.peliculasfront.model.Actor;
import es.uah.peliculasfront.model.Pelicula;
import es.uah.peliculasfront.model.PeliculasActores;
import es.uah.peliculasfront.paginator.PageRender;
import es.uah.peliculasfront.service.IPeliculasService;
import es.uah.peliculasfront.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    IPeliculasService peliculasService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("")
    public String listadoPeliculas(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 8);
        Page<Pelicula> peliculas = peliculasService.buscarTodas(pageable);
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/peliculas", peliculas);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRender);
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

        Pelicula peliculaGuardada = peliculasService.buscarPeliculaPorId(id);
        Pelicula nuevaPelicula = peliculasService.buscarPeliculaPorId(id);
        model.addAttribute("peliculaGuardada", peliculaGuardada);
        model.addAttribute("nuevaPelicula", nuevaPelicula);
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
                                 @RequestParam(name = "tipo") int tipo,
                                 @RequestParam(name = "buscarPor") String buscarPor,
                                 @RequestParam(name = "page", defaultValue = "0") int page) {


        Pageable pageable = PageRequest.of(page, 8);
        Page<Pelicula> peliculas;
        switch (tipo) {
            case 0: //búsqueda por título
                peliculas = peliculasService.buscarPeliculasPorTitulo(buscarPor, pageable);
                model.addAttribute("tipo", "título");
                break;
            case 1: //búsqueda por género
                peliculas = peliculasService.buscarPeliculasPorGenero(buscarPor, pageable);
                model.addAttribute("tipo", "género");
                break;
            case 2: //búsqueda por actor
                peliculas = peliculasService.buscarPeliculasPorActor(buscarPor, pageable);
                model.addAttribute("tipo", "actor");
                break;
            default:
                peliculas = null;
        }
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/peliculas/buscar", peliculas);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("busqueda", buscarPor);
        model.addAttribute("page", pageRender);
        return "views/busquedaPeliculas";
    }


    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }


    @PostMapping("/guardar/")
    public String guardarPelicula(Model model,
                                  Pelicula nuevaPelicula,
                                  @RequestParam("file") MultipartFile portada,
                                  RedirectAttributes atributos) {

        if (!portada.isEmpty()) {
            if (nuevaPelicula.getId() != null && nuevaPelicula.getId() > 0 && nuevaPelicula.getPortada() != null &&
                    nuevaPelicula.getPortada().length() > 0) {
                uploadFileService.delete(nuevaPelicula.getPortada());
            }
            String uniqueFilename = null;
            try{
                uniqueFilename = uploadFileService.copy(portada);
            }catch (Exception e){
                e.printStackTrace();
            }

            atributos.addFlashAttribute("msg", "Se ha subido correctamente" + uniqueFilename);
            nuevaPelicula.setPortada(uniqueFilename);
        }

        peliculasService.guardarPelicula(nuevaPelicula);
        atributos.addFlashAttribute("msg","Los datos de la película fueron guardados");
        return "redirect:/peliculas";

    }

    @GetMapping("/borrar/{id}")
    public String eliminarPelicula(Model model,
                                   @PathVariable("id") Integer id,
                                   RedirectAttributes atributos){

        //Para borrar la portada de la aplicación
        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        if (uploadFileService.delete(pelicula.getPortada())) {
            atributos.addFlashAttribute("msg", "Imagen " + pelicula.getPortada() + " eliminada con exito!");
        }

        //Para borrar la pelicula de la BBDD
        peliculasService.eliminarPelicula(id);
        atributos.addFlashAttribute("msg", "Los datos de la película fueron borrados");

        return "redirect:/peliculas";
    }



    @GetMapping("/asociar/")
    public String asociarPeliculaActor(PeliculasActores peliculasActores) {

        peliculasService.asociar(peliculasActores.getIdPelicula(), peliculasActores.getIdActor());
        System.out.println(peliculasActores.getIdPelicula() + " " + peliculasActores.getIdActor());
        return "redirect:/";
    }


    private String formatearActores(Pelicula pelicula) {
        String formateado = "";
        if(pelicula.getActores().size()>0){
            for (int i = 0; i < pelicula.getActores().size() - 1; i++) {
                formateado += pelicula.getActores().get(i).getNombre() + ", ";
            }
            formateado += pelicula.getActores().get(pelicula.getActores().size() - 1).getNombre();
        }
        return formateado;
    }


}

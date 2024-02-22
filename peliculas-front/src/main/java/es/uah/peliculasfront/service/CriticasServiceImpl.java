package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Actor;
import es.uah.peliculasfront.model.Pelicula;
import es.uah.peliculasfront.model.Critica;
import es.uah.peliculasfront.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CriticasServiceImpl implements ICriticasService {

    @Autowired
    RestTemplate template;

    @Autowired
    IActoresService actoresService;

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    IPeliculasService peliculasService;

    String url = "http://localhost:8090/api/usuarios/criticas";

    @Override
    public Page<Critica> buscarTodas(Pageable pageable) {
        Critica[] criticas = template.getForObject(url, Critica[].class);
        List<Critica> criticasList = Arrays.asList(criticas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Critica> list;

        if(criticasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }
        Page<Critica> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticasList.size());
        return page;
    }

    @Override
    public Page<Critica> buscarCriticasPorIdPelicula(Integer idPelicula, Pageable pageable) {
        Critica[] criticas = template.getForObject(url+"/pelicula/"+idPelicula, Critica[].class);
        List<Critica> criticasList = Arrays.asList(criticas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Critica>list;

        if(criticasList.size() <startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }
        Page<Critica> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticas.length);
        return page;
    }

    @Override
    public Critica buscarCriticaPorId(Integer idCritica) {
        Critica critica = template.getForObject(url+"/"+idCritica, Critica.class);
        return critica;
    }

    @Override
    public String guardarCritica(Critica critica) {
        /**
         * Modificar este método más adelante

        if (critica.getIdCritica() != null && critica.getIdCritica() > 0) {
            return "No se puede modificar una matrícula.";
        } else {
            //Inscribimos al actor en la pelicula
            Usuario usuario = usuariosService.buscarUsuarioPorId(critica.getUsuario().getIdUsuario());
            Actor actor = actoresService.buscarActorPorCorreo(usuario.getCorreo());
            Pelicula pelicula = peliculasService.buscarPeliculaPorId(critica.getIdPelicula());
            String resultado="";
            //si no existe el actor, lo creamos
            if(actor == null) {
                actor = new Ac(usuario.getNombre(), usuario.getCorreo());
                alumnosService.guardarAlumno(alumno);
                resultado = "Alumno creado. ";
            } else { //si existe, comprobamos que no se matricula dos veces en el mismo curso
                resultado = "Alumno encontrado. ";
                List<Curso> cursos = alumno.getCursos();
                if (cursos.contains(curso)) {
                    return "El alumno ya existe en el curso!";
                }
            }
            alumnosService.inscribirCurso(alumno.getIdAlumno(), matricula.getIdCurso());
            //Guardamos la matrícula
            matricula.setPrecio(curso.getPrecio());
            matricula.setIdMatricula(0);
            matricula.setFecha(new Date());
            template.postForObject(url, matricula, String.class);
            return resultado + "Los datos de la matricula fueron guardados!";
        }
         */
        return "";
    }

    @Override
    public void eliminarCritica(Integer idCritica) {
        template.delete(url+ "/" +  idCritica);
    }

}

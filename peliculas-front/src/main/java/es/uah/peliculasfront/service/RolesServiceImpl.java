package es.uah.peliculasfront.service;

import es.uah.peliculasfront.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RolesServiceImpl implements IRolesService {

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8090/api/usuarios/roles";

    @Override
    public List<Rol> buscarTodos() {
        Rol[] roles = template.getForObject(url, Rol[].class);
        return Arrays.asList(roles);
    }

    @Override
    public Rol buscarRolPorId(Integer idRol) {
        Rol rol = template.getForObject(url+"/"+idRol, Rol.class);
        return rol;
    }

    @Override
    public void guardarRol(Rol rol) {
        if (rol.getIdRol() != null && rol.getIdRol() > 0) {
            template.put(url, rol);
        } else {
            rol.setIdRol(0);
            template.postForObject(url, rol, String.class);
        }
    }

    @Override
    public void eliminarRol(Integer idRol) {
        template.delete(url + "/" + idRol);
    }
}

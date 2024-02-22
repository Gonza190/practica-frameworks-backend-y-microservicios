package es.uah.peliculasusuarios.controller;

import es.uah.peliculasusuarios.model.Rol;
import es.uah.peliculasusuarios.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class RolesController {

    @Autowired
    IRolesService rolesService;

    @GetMapping("/roles")
    public List<Rol> buscarTodos() {
        return rolesService.buscarTodos();
    }

    @GetMapping("/roles/{id}")
    public Rol buscarRolPorId(@PathVariable("id") Integer id) {
        return rolesService.buscarRolPorId(id);
    }


}

package es.uah.peliculasusuarios.service;

import es.uah.peliculasusuarios.model.Rol;

import java.util.List;

public interface IRolesService {

    Rol buscarRolPorId(int id);
    List<Rol> buscarTodos();


}

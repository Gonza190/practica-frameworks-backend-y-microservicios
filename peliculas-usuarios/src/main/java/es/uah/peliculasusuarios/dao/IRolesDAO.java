package es.uah.peliculasusuarios.dao;

import es.uah.peliculasusuarios.model.Rol;

import java.util.List;

public interface IRolesDAO {


    List<Rol> buscarTodos();

    Rol buscarRolPorId(Integer id);

}

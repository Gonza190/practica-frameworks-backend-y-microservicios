package es.uah.peliculasusuarios.service;

import es.uah.peliculasusuarios.dao.IRolesDAO;
import es.uah.peliculasusuarios.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImp implements IRolesService{

    @Autowired
    IRolesDAO rolesDAO;

    @Override
    public Rol buscarRolPorId(int id) {
        return rolesDAO.buscarRolPorId(id);
    }

    @Override
    public List<Rol> buscarTodos() {
        return rolesDAO.buscarTodos();
    }
}

package es.uah.peliculasusuarios.dao;

import es.uah.peliculasusuarios.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolesDAOImp implements IRolesDAO{

    @Autowired
    IRolesJPA rolesJPA;


    @Override
    public List<Rol> buscarTodos() {
        return rolesJPA.findAll();
    }

    @Override
    public Rol buscarRolPorId(Integer id) {
        Optional<Rol> optional = rolesJPA.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}

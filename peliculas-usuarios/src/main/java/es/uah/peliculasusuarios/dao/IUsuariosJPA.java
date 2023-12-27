package es.uah.peliculasusuarios.dao;

import es.uah.peliculasusuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuariosJPA extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

    Usuario findByCorreo(String correo);
    Usuario findByCorreoAndPassword(String correo, String password);

}

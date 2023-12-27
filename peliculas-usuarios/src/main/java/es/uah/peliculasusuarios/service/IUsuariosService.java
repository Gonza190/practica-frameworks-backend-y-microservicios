package es.uah.peliculasusuarios.service;

import es.uah.peliculasusuarios.model.Usuario;

import java.util.List;

public interface IUsuariosService {

    List<Usuario> buscarTodos();

    Usuario buscarUsuarioPorId(Integer idUsuario);

    Usuario buscarUsuarioPorUsername(String username);

    Usuario buscarUsuarioPorCorreo(String correo);

    void guardarUsuario(Usuario usuario);

    void eliminarUsuario(Integer idUsuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarCritica(Integer idUsuario, Integer idCritica);

    Usuario buscarUsuarioPorCorreoPassword(String correo, String password);

}

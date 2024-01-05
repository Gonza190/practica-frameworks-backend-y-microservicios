package es.uah.peliculasfront.service;
import es.uah.peliculasfront.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IUsuariosService {
    Page<Usuario> buscarTodos(Pageable pageable);
    Usuario buscarUsuarioPorId(Integer idUsuario);
    Usuario buscarUsuarioPorNombre(String nombre);
    Usuario buscarUsuarioPorCorreo(String correo);
    Usuario login(String correo, String clave);
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Integer idUsuario);
}
package es.uah.peliculasusuarios.controller;

import es.uah.peliculasusuarios.model.Usuario;
import es.uah.peliculasusuarios.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuariosController {

    @Autowired
    IUsuariosService usuariosService;

    @GetMapping("/usuarios")
    public List<Usuario> buscarTodos() {
        return usuariosService.buscarTodos();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable("id") Integer id) {
        return usuariosService.buscarUsuarioPorId(id);
    }

    @GetMapping("/usuarios/correo/{correo}")
    public Usuario buscarUsuarioPorCorreo(@PathVariable("correo") String correo) {
        return usuariosService.buscarUsuarioPorCorreo(correo);
    }

    @GetMapping("/usuarios/nombre/{nombre}")
    public Usuario buscarUsuarioPorNombre(@PathVariable("nombre") String nombre) {
        return usuariosService.buscarUsuarioPorUsername(nombre);
    }

    @PostMapping("/usuarios")
    public void guardarUsuario(@RequestBody Usuario usuario) {
        usuariosService.guardarUsuario(usuario);
    }

    @PutMapping("/usuarios")
    public void actualizarUsuario(@RequestBody Usuario usuario) {
        usuariosService.actualizarUsuario(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable("id") Integer id) {
        usuariosService.eliminarUsuario(id);
    }

}

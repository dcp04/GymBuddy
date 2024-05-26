package gymbuddy.app.service;

import gymbuddy.app.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Long id);
    Usuario updateUsuario(Long id, Usuario usuario);
    void deleteUsuario(Long id);
}

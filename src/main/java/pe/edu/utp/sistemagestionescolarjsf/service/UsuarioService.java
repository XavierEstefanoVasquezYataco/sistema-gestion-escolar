package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Usuario;
import java.util.List;

@Local
public interface UsuarioService {
    Usuario autenticar(String username, String password);
    void registrarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Integer id);
    Usuario buscarPorId(Integer id);
    List<Usuario> listarTodos();
}
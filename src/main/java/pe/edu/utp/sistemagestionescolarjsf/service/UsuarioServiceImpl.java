package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.UsuarioDAO;
import pe.edu.utp.entity.Usuario;
import pe.edu.utp.util.PasswordUtil;
import java.util.List;
import java.util.Optional;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario autenticar(String username, String password) {
        Optional<Usuario> optUsuario = usuarioDAO.findByUsername(username);
        
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            
            // Validación dual: Permite el ingreso con las contraseñas en texto plano 
            // generadas en el script SQL inicial, o con contraseñas nuevas encriptadas.
            if (usuario.getPassword().equals(password) || PasswordUtil.verifyPassword(password, usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuario.setPassword(PasswordUtil.hashPassword(usuario.getPassword()));
        usuarioDAO.create(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        Usuario usuarioBD = buscarPorId(usuario.getId());
        
        // Solo encripta si la contraseña fue modificada
        if (usuarioBD != null && !usuarioBD.getPassword().equals(usuario.getPassword())) {
            usuario.setPassword(PasswordUtil.hashPassword(usuario.getPassword()));
        }
        
        usuarioDAO.update(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioDAO.deleteById(id);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }
}
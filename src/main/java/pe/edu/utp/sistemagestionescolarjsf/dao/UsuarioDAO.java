package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Usuario;
import java.util.Optional;

@Local
public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
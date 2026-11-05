package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Rol;
import java.util.List;

@Local
public interface RolService {
    void registrarRol(Rol rol);
    void actualizarRol(Rol rol);
    void eliminarRol(Integer id);
    List<Rol> listarTodos();
}
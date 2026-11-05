package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Docente;
import java.util.List;

@Local
public interface DocenteService {
    void registrarDocente(Docente docente);
    void actualizarDocente(Docente docente);
    void eliminarDocente(Integer id);
    Docente buscarPorId(Integer id);
    List<Docente> listarTodos();
}
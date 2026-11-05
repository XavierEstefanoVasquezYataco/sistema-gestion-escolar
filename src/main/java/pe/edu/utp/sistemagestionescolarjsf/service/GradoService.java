package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Grado;
import java.util.List;

@Local
public interface GradoService {
    List<Grado> listarTodos();
}
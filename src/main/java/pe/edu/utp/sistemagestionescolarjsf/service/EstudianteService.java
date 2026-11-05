package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Estudiante;
import java.util.List;

@Local
public interface EstudianteService {
    void registrarEstudiante(Estudiante estudiante);
    void actualizarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(Integer id);
    Estudiante buscarPorId(Integer id);
    List<Estudiante> listarTodos();
}
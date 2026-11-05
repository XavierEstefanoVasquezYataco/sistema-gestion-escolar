package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Curso;
import java.util.List;

@Local
public interface CursoService {
    void registrarCurso(Curso curso);
    void actualizarCurso(Curso curso);
    void eliminarCurso(Integer id);
    Curso buscarPorId(Integer id);
    List<Curso> listarTodos();
}
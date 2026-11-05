package pe.edu.utp.service;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Matricula;
import java.util.List;
@Local
public interface MatriculaService {
    void registrarMatricula(Matricula matricula);
    void actualizarMatricula(Matricula matricula);
    void eliminarMatricula(Integer id);
    List<Matricula> listarTodos();
}
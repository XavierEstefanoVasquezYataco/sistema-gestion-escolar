package pe.edu.utp.service;
import jakarta.ejb.Local;
import pe.edu.utp.entity.DocenteCurso;
import java.util.List;
@Local
public interface DocenteCursoService { List<DocenteCurso> listarTodos(); }
package pe.edu.utp.service;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Nota;
import java.util.List;

@Local
public interface NotaService {
    void registrarNota(Nota nota);
    void actualizarNota(Nota nota);
    void eliminarNota(Integer id);
    List<Nota> listarTodas();
}
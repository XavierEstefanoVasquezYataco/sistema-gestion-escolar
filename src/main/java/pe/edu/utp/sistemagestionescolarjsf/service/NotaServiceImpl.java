package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.NotaDAO;
import pe.edu.utp.entity.Nota;
import java.util.List;

@Stateless
public class NotaServiceImpl implements NotaService {

    @Inject
    private NotaDAO notaDAO;

    @Override
    public void registrarNota(Nota nota) {
        notaDAO.create(nota);
    }

    @Override
    public void actualizarNota(Nota nota) {
        notaDAO.update(nota);
    }

    @Override
    public void eliminarNota(Integer id) {
        notaDAO.deleteById(id);
    }

    @Override
    public List<Nota> listarTodas() {
        return notaDAO.findAll();
    }
}
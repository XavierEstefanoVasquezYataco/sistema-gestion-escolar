package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.GradoDAO;
import pe.edu.utp.entity.Grado;
import java.util.List;

@Stateless
public class GradoServiceImpl implements GradoService {
    
    @Inject
    private GradoDAO gradoDAO;

    @Override
    public List<Grado> listarTodos() {
        return gradoDAO.findAll();
    }
}
package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.RolDAO;
import pe.edu.utp.entity.Rol;
import java.util.List;

@Stateless
public class RolServiceImpl implements RolService {
    
    @Inject
    private RolDAO rolDAO;

    @Override
    public void registrarRol(Rol rol) {
        rolDAO.create(rol);
    }

    @Override
    public void actualizarRol(Rol rol) {
        rolDAO.update(rol);
    }

    @Override
    public void eliminarRol(Integer id) {
        rolDAO.deleteById(id);
    }

    @Override
    public List<Rol> listarTodos() {
        return rolDAO.findAll();
    }
}
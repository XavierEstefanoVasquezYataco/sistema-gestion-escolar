package pe.edu.utp.dao;

import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Rol;

@Stateless
public class RolDAOImpl extends GenericDAOImpl<Rol, Integer> implements RolDAO {
    public RolDAOImpl() {
        super(Rol.class);
    }
}
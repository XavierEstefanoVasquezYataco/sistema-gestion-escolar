package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Rol;

@Local
public interface RolDAO extends GenericDAO<Rol, Integer> {
}
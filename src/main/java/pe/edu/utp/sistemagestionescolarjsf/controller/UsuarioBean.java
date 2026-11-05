package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.Rol;
import pe.edu.utp.entity.Usuario;
import pe.edu.utp.service.RolService;
import pe.edu.utp.service.UsuarioService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private RolService rolService;

    private List<Usuario> usuarios;
    private List<Rol> roles;
    private Usuario usuarioSeleccionado;
    private Integer idRolSeleccionado;

    @PostConstruct
    public void init() {
        usuarios = usuarioService.listarTodos();
        roles = rolService.listarTodos();
    }

    public void nuevoUsuario() {
        usuarioSeleccionado = new Usuario();
        usuarioSeleccionado.setActivo(true);
        idRolSeleccionado = null;
    }

    public void guardarUsuario() {
        try {
            Rol rol = roles.stream()
                    .filter(r -> r.getId().equals(idRolSeleccionado))
                    .findFirst()
                    .orElse(null);
            usuarioSeleccionado.setRol(rol);

            if (usuarioSeleccionado.getId() == null) {
                usuarioService.registrarUsuario(usuarioSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Usuario Creado Correctamente"));
            } else {
                usuarioService.actualizarUsuario(usuarioSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Usuario Actualizado Correctamente"));
            }
            
            usuarios = usuarioService.listarTodos();
            PrimeFaces.current().executeScript("PF('dialogoUsuario').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-usuarios");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el usuario"));
        }
    }

    public void eliminarUsuario() {
        try {
            usuarioService.eliminarUsuario(usuarioSeleccionado.getId());
            usuarios.remove(usuarioSeleccionado);
            usuarioSeleccionado = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Usuario Eliminado Correctamente"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-usuarios");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el usuario. Es posible que tenga registros asociados."));
        }
    }

    // Getters y Setters
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
    public List<Rol> getRoles() { return roles; }
    public void setRoles(List<Rol> roles) { this.roles = roles; }
    public Usuario getUsuarioSeleccionado() { return usuarioSeleccionado; }
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) { this.usuarioSeleccionado = usuarioSeleccionado; }
    public Integer getIdRolSeleccionado() { return idRolSeleccionado; }
    public void setIdRolSeleccionado(Integer idRolSeleccionado) { this.idRolSeleccionado = idRolSeleccionado; }
}
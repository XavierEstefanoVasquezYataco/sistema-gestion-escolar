package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.Rol;
import pe.edu.utp.service.RolService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RolBean implements Serializable {

    @Inject
    private RolService rolService;

    private List<Rol> roles;
    private Rol rolSeleccionado;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        roles = rolService.listarTodos();
    }

    public void nuevoRol() {
        rolSeleccionado = new Rol();
    }

    public void guardarRol() {
        try {
            // Convertimos a mayúsculas por estándar
            rolSeleccionado.setNombre(rolSeleccionado.getNombre().toUpperCase());

            if (rolSeleccionado.getId() == null) {
                rolService.registrarRol(rolSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Rol registrado correctamente"));
            } else {
                rolService.actualizarRol(rolSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Rol actualizado correctamente"));
            }
            
            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoRol').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-roles");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre del rol ya existe."));
        }
    }

    public void eliminarRol() {
        try {
            rolService.eliminarRol(rolSeleccionado.getId());
            cargarDatos();
            rolSeleccionado = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Rol eliminado correctamente"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-roles");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede eliminar un rol que ya está asignado a usuarios."));
        }
    }

    public List<Rol> getRoles() { return roles; }
    public void setRoles(List<Rol> roles) { this.roles = roles; }
    public Rol getRolSeleccionado() { return rolSeleccionado; }
    public void setRolSeleccionado(Rol rolSeleccionado) { this.rolSeleccionado = rolSeleccionado; }
}
package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.Docente;
import pe.edu.utp.service.DocenteService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DocenteBean implements Serializable {

    @Inject
    private DocenteService docenteService;

    private List<Docente> docentes;
    private Docente docenteSeleccionado;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        docentes = docenteService.listarTodos();
    }

    public void nuevoDocente() {
        docenteSeleccionado = new Docente();
    }

    public void guardarDocente() {
        try {
            if (docenteSeleccionado.getId() == null) {
                docenteService.registrarDocente(docenteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Docente registrado. Cuenta generada con prefijo 'd.' y clave 'D'+DNI."));
            } else {
                docenteService.actualizarDocente(docenteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Docente actualizado correctamente"));
            }
            
            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoDocente').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-docentes");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El DNI ya existe o ocurrió un problema."));
        }
    }

    public void eliminarDocente() {
        try {
            docenteService.eliminarDocente(docenteSeleccionado.getId());
            cargarDatos();
            docenteSeleccionado = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Docente eliminado correctamente"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-docentes");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar. El docente tiene cursos asignados."));
        }
    }

    public List<Docente> getDocentes() { return docentes; }
    public void setDocentes(List<Docente> docentes) { this.docentes = docentes; }
    public Docente getDocenteSeleccionado() { return docenteSeleccionado; }
    public void setDocenteSeleccionado(Docente docenteSeleccionado) { this.docenteSeleccionado = docenteSeleccionado; }
}
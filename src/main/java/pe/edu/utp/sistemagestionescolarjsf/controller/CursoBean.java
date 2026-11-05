package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.Curso;
import pe.edu.utp.entity.Grado;
import pe.edu.utp.service.CursoService;
import pe.edu.utp.service.GradoService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CursoBean implements Serializable {

    @Inject
    private CursoService cursoService;

    @Inject
    private GradoService gradoService;

    private List<Curso> cursos;
    private List<Grado> gradosDisponibles;
    private Curso cursoSeleccionado;
    private Integer idGradoSeleccionado;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        cursos = cursoService.listarTodos();
        gradosDisponibles = gradoService.listarTodos();
    }

    public void nuevoCurso() {
        cursoSeleccionado = new Curso();
        idGradoSeleccionado = null;
    }

    public void guardarCurso() {
        try {
            Grado grado = gradosDisponibles.stream()
                    .filter(g -> g.getId().equals(idGradoSeleccionado))
                    .findFirst()
                    .orElse(null);
            
            cursoSeleccionado.setGrado(grado);

            if (cursoSeleccionado.getId() == null) {
                cursoService.registrarCurso(cursoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Curso registrado correctamente"));
            } else {
                cursoService.actualizarCurso(cursoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Curso actualizado correctamente"));
            }
            
            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoCurso').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-cursos");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el curso."));
        }
    }

    public void eliminarCurso() {
        try {
            cursoService.eliminarCurso(cursoSeleccionado.getId());
            cargarDatos();
            cursoSeleccionado = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Curso eliminado correctamente"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-cursos");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar. El curso tiene registros asociados (notas/horarios)."));
        }
    }

    public List<Curso> getCursos() { return cursos; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }
    public List<Grado> getGradosDisponibles() { return gradosDisponibles; }
    public void setGradosDisponibles(List<Grado> gradosDisponibles) { this.gradosDisponibles = gradosDisponibles; }
    public Curso getCursoSeleccionado() { return cursoSeleccionado; }
    public void setCursoSeleccionado(Curso cursoSeleccionado) { this.cursoSeleccionado = cursoSeleccionado; }
    public Integer getIdGradoSeleccionado() { return idGradoSeleccionado; }
    public void setIdGradoSeleccionado(Integer idGradoSeleccionado) { this.idGradoSeleccionado = idGradoSeleccionado; }
}
package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.*;
import pe.edu.utp.service.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class PagoBean implements Serializable {

    @Inject private PagoService pagoService;
    @Inject private MatriculaService matriculaService;
    @Inject private TipoPagoService tipoPagoService;

    private List<Pago> pagos;
    private List<Matricula> matriculas;
    private List<TipoPago> tiposPago;

    private Pago pagoSeleccionado;
    private Integer idMatricula;
    private Integer idTipoPago;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        pagos = pagoService.listarTodos();
        matriculas = matriculaService.listarTodos().stream()
                .filter(m -> "ACTIVA".equals(m.getEstado()))
                .toList();
        tiposPago = tipoPagoService.listarTodos();
    }

    public void nuevoPago() {
        pagoSeleccionado = new Pago();
        pagoSeleccionado.setEstado(EstadoPago.PENDIENTE);
        idMatricula = null;
        idTipoPago = null;
    }

    public void actualizarMonto() {
        if (idTipoPago != null) {
            TipoPago tp = tiposPago.stream().filter(t -> t.getId().equals(idTipoPago)).findFirst().orElse(null);
            if (tp != null) {
                pagoSeleccionado.setMonto(tp.getMontoReferencial());
            }
        }
    }

    public void guardarPago() {
        try {
            Matricula m = new Matricula(); m.setId(idMatricula);
            TipoPago tp = new TipoPago(); tp.setId(idTipoPago);

            pagoSeleccionado.setMatricula(m);
            pagoSeleccionado.setTipoPago(tp);

            if (pagoSeleccionado.getEstado() == EstadoPago.PAGADO && pagoSeleccionado.getFechaPago() == null) {
                pagoSeleccionado.setFechaPago(LocalDateTime.now());
            }

            if (pagoSeleccionado.getId() == null) {
                pagoService.registrarPago(pagoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Pago registrado correctamente"));
            } else {
                pagoService.actualizarPago(pagoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Pago actualizado correctamente"));
            }
            
            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoPago').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-pagos");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Verifique el número de operación único."));
        }
    }

    public void eliminarPago() {
        try {
            pagoService.eliminarPago(pagoSeleccionado.getId());
            cargarDatos();
            pagoSeleccionado = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Registro de pago eliminado"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-pagos");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el pago."));
        }
    }

    public List<Pago> getPagos() { return pagos; }
    public void setPagos(List<Pago> pagos) { this.pagos = pagos; }
    public List<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }
    public List<TipoPago> getTiposPago() { return tiposPago; }
    public void setTiposPago(List<TipoPago> tiposPago) { this.tiposPago = tiposPago; }
    public Pago getPagoSeleccionado() { return pagoSeleccionado; }
    public void setPagoSeleccionado(Pago pagoSeleccionado) { this.pagoSeleccionado = pagoSeleccionado; }
    public Integer getIdMatricula() { return idMatricula; }
    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }
    public Integer getIdTipoPago() { return idTipoPago; }
    public void setIdTipoPago(Integer idTipoPago) { this.idTipoPago = idTipoPago; }
}
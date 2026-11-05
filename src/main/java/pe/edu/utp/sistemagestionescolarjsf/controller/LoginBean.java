package pe.edu.utp.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.entity.Usuario;
import pe.edu.utp.service.UsuarioService;
import pe.edu.utp.util.SessionUtils;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    // Cambiado de 'username' a 'usuario' para hacer match con tu login.xhtml
    private String usuario;
    private String password;
    private Usuario usuarioActual;

    @Inject
    private UsuarioService usuarioService;

    public String login() {
        // Usa la variable 'usuario' para buscar en la BD
        usuarioActual = usuarioService.autenticar(usuario, password);
        
        if (usuarioActual != null) {
            HttpSession session = SessionUtils.getSession();
            // Si la sesión es nula (primera vez), forzamos su creación
            if (session == null) {
                session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            }
            session.setAttribute("usuarioLogueado", usuarioActual);
            
            return "dashboard?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso", "Usuario o contraseña incorrectos o inactivos."));
            return null;
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        if (session != null) {
            session.invalidate();
        }
        usuarioActual = null;
        return "login?faces-redirect=true";
    }

    // Getters y Setters actualizados
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
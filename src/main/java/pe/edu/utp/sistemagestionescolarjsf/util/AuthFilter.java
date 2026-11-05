package pe.edu.utp.util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.entity.Usuario;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
            
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String loginURI = context + "/login.xhtml";
        
        boolean loggedIn = session != null && session.getAttribute("usuarioLogueado") != null;
        boolean loginRequest = uri.equals(loginURI);
        boolean resourceRequest = uri.contains("jakarta.faces.resource") || uri.contains("/resources/");

        if (loginRequest || resourceRequest) {
            chain.doFilter(request, response);
            return;
        }

        if (!loggedIn) {
            res.sendRedirect(loginURI);
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        String rol = usuario.getRol().getNombre().toUpperCase();
        boolean isAutorizado = false;

        if (uri.equals(context + "/dashboard.xhtml") || uri.equals(context + "/")) {
            isAutorizado = true;
        } 
        else if (rol.equals("ADMIN")) {
            isAutorizado = true;
        } 
        else if (rol.equals("DOCENTE")) {
            // AQUÍ AGREGAMOS LAS NUEVAS RUTAS DEL DOCENTE PARA QUE NO LO BLOQUEE
            if (uri.contains("/notas.xhtml") || 
                uri.contains("/estudiantes.xhtml") || 
                uri.contains("/pagos.xhtml") ||
                uri.contains("/mis-cursos.xhtml") ||
                uri.contains("/perfil-docente.xhtml")) { 
                isAutorizado = true;
            }
        } 
        else if (rol.equals("ESTUDIANTE")) {
            if (uri.contains("/mis-notas.xhtml") || 
                uri.contains("/mis-pagos.xhtml") || 
                uri.contains("/mi-perfil.xhtml")) {
                isAutorizado = true;
            }
        }

        if (isAutorizado) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(context + "/dashboard.xhtml");
        }
    }
}
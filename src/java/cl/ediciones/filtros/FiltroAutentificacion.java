package cl.ediciones.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FiltroAutentificacion implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        
        HttpServletRequest hsr = (HttpServletRequest) request;
        String uri = hsr.getRequestURI();
        
        if(!(uri.indexOf("autentificar")>0)){
            HttpSession sesion = hsr.getSession(false);
            if(sesion==null || sesion.getAttribute("usuario")==null){
                RequestDispatcher rd = hsr.getRequestDispatcher("/index_1.jsp");
                rd.forward(request,response);
                return;
            }
            
        }
        chain.doFilter(request,response);
    }
    
    public void destroy() {
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}


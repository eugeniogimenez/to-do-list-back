package logica;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Método de inicialización del filtro (opcional)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Establecer los encabezados CORS para permitir todas las solicitudes desde cualquier origen
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Continuar con la cadena de filtros y el procesamiento del servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Método de destrucción del filtro (opcional)
    }
}

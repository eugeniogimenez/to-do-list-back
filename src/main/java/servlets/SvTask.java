package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Task;

@WebServlet(name = "SvTask", urlPatterns = {"/SvTask"})
public class SvTask extends HttpServlet {

    ControladoraLogica controlLogica = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    //READ
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // Obtener el ID de la tarea de los parámetros de la URL
        String taskIdParam = request.getParameter("id");

        // Validar que el parámetro "id" sea un número entero
        int taskId;
        try {
            taskId = Integer.parseInt(taskIdParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("El parámetro 'id' debe ser un número entero válido.");
            return;
        }

        // Llamar al método de la lógica para obtener la tarea por su ID
        Task task = controlLogica.obtenerTarea(taskId);

        // Verificar si se encontró la tarea
        if (task != null) {
            // Configurar la respuesta con el código de estado 200 (OK) y el cuerpo de la respuesta como JSON

            Gson gson = new Gson(); // Instancio Gson
            String jsonTask = gson.toJson(task); // Convierto Task a JSON
            response.setStatus(HttpServletResponse.SC_OK); // Configuro la respuesta de estado como 200 OK
            response.setContentType("application/json"); // Establezco la respuesta como JSON
            response.getWriter().write(jsonTask); // Escribir el JSON de la tarea en el cuerpo de la respuesta

        } else {
            // Configurar la respuesta con el código de estado 404 (Not Found) si la tarea no se encontró
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    //CREATE
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Obtener el cuerpo de la solicitud. Nos da el JSON como una cadena de texto
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Utilizar Gson para convertir el JSON en un objeto Task
        Gson gson = new Gson();
        Task task = gson.fromJson(requestBody, Task.class);

        // Llamar al método de la lógica para crear la tarea
        controlLogica.crearTarea(task);

        // Configurar la respuesta con el código de estado 201 (CREATED)
        response.setStatus(HttpServletResponse.SC_CREATED);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // DELETE
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Obtener el ID de la tarea de los parámetros de la URL
        String taskIdParam = request.getParameter("id");

        // Validar que el parámetro "id" sea un número entero
        int taskId;
        try {
            taskId = Integer.parseInt(taskIdParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("El parámetro 'id' debe ser un número entero válido.");
            return;
        }

        // Llamar al método de la lógica para eliminar la tarea
        try {
            controlLogica.eliminarTarea(taskId);
            // La eliminación no arrojó ninguna excepción
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            // Ocurrió un error durante la eliminación
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Obtener el cuerpo de la solicitud. Nos da el JSON como una cadena de texto
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Utilizar Gson para convertir el JSON en un objeto Task
        Gson gson = new Gson();
        Task task = gson.fromJson(requestBody, Task.class);

        // Llamar al método de la lógica para actualizar la tarea
        try {
            controlLogica.actualizarTarea(task);
            // La actualización no arrojó ninguna excepción
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            // Ocurrió un error durante la actualización
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}

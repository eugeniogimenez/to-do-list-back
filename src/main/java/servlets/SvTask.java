package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Task;
import logica.TaskUpdateRequest;

@WebServlet(name = "SvTask", urlPatterns = {"/SvTask/*"})
public class SvTask extends HttpServlet {

    ControladoraLogica controlLogica = new ControladoraLogica();

    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }*/
    //READ
    // READ a specific task
    protected void getTask(HttpServletRequest request, HttpServletResponse response)
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

        // Llamar al método de la lógica para obtener la tarea específica
        Task task = controlLogica.obtenerTarea(taskId);

        if (task != null) {
            // Configurar la respuesta con el código de estado 200 (OK) y el cuerpo de la respuesta como JSON
            Gson gson = new Gson();
            String jsonTask = gson.toJson(task);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write(jsonTask);
        } else {
            // La tarea no se encontró
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // READ incomplete tasks
    protected void getIncompleteTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Llamar al método de la lógica para obtener las tareas incompletas
        List<Task> incompleteTasks = controlLogica.obtenerTareasIncompletas();

        // Configurar la respuesta con el código de estado 200 (OK) y el cuerpo de la respuesta como JSON
        Gson gson = new Gson();
        String jsonIncompleteTasks = gson.toJson(incompleteTasks);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(jsonIncompleteTasks);
    }

// READ completed tasks
    protected void getCompletedTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Llamar al método de la lógica para obtener las tareas completadas
        List<Task> completedTasks = controlLogica.obtenerTareasCompletadas();

        // Configurar la respuesta con el código de estado 200 (OK) y el cuerpo de la respuesta como JSON
        Gson gson = new Gson();
        String jsonCompletedTasks = gson.toJson(completedTasks);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(jsonCompletedTasks);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Obtener el parámetro "completed" de la URL
        String completedParam = request.getParameter("completed");

        // Obtener el parámetro "id" de la URL
        String taskIdParam = request.getParameter("id");

        if (taskIdParam != null) {
            // Si se proporcionó el parámetro "id", obtener una tarea específica
            getTask(request, response);
        } else if (completedParam != null && completedParam.equalsIgnoreCase("true")) {
            // Si se proporcionó el parámetro "completed" y es "true", obtener las tareas completadas
            getCompletedTasks(request, response);
        } else {
            // Obtener las tareas incompletas por defecto
            getIncompleteTasks(request, response);
        }
    }

    //CREATE
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar el encabezado Access-Control-Allow-Origin para permitir el acceso desde http://localhost:1234
        response.setHeader("Access-Control-Allow-Origin", "*");

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

    /*@Override
    public String getServletInfo() {
        return "Short description";
    }*/
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
        //response.setHeader("Access-Control-Allow-Origin", "http://localhost:1234");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Obtener el cuerpo de la solicitud. Nos da el JSON como una cadena de texto
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Utilizar Gson para convertir el JSON en un objeto TaskUpdateRequest
        Gson gson = new Gson();
        TaskUpdateRequest TaskupdateRequest = gson.fromJson(requestBody, TaskUpdateRequest.class);

        // Obtener el ID de la tarea y la tarea actualizada del objeto TaskUpdateRequest
        int taskIdUpdate = TaskupdateRequest.getId();
        Task updatedTask = TaskupdateRequest.getTask();

        // Llamar al método de la lógica para obtener la tarea existente
        Task existingTask = controlLogica.obtenerTarea(taskIdUpdate);

        if (existingTask != null) {
            // Establecer los nuevos valores de la tarea existente
            existingTask.setId(updatedTask.getId());
            existingTask.setDescripcion(updatedTask.getDescripcion());
            existingTask.setCompleted(updatedTask.isCompleted());

            // Llamar al método de la lógica para actualizar la tarea
            try {
                controlLogica.actualizarTarea(existingTask);
                // La actualización no arrojó ninguna excepción
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (Exception e) {
                // Ocurrió un error durante la actualización
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // La tarea no se encontró
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}

package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ControladoraPersistencia;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraLogica {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    //CREATE
    public void crearTarea(Task task) {

        controlPersis.crearTarea(task);
    }

    //READ
    public Task obtenerTarea(int id) {

        return controlPersis.obtenerTarea(id);
    }

    //UPDATE
    public void actualizarTarea(Task tarea) {

        controlPersis.actualizarTarea(tarea);
    }

    //DESTROY
    public void eliminarTarea(int id) {

        controlPersis.eliminarTarea(id);
    }

    //READ TAREAS PENDIENTES
    public List<Task> obtenerTareasIncompletas() {
        List<Task> tareasIncompletas = new ArrayList<>();
        List<Task> todasLasTareas = controlPersis.obtenerTodasLasTareas();

        for (Task tarea : todasLasTareas) {
            if (!tarea.isCompleted()) {
                tareasIncompletas.add(tarea);
            }
        }

        return tareasIncompletas;
    }

    //READ TAREAS COMPLETADAS
    public List<Task> obtenerTareasCompletadas() {
        List<Task> tareasCompletadas = new ArrayList<>();
        List<Task> todasLasTareas = controlPersis.obtenerTodasLasTareas();

        for (Task tarea : todasLasTareas) {
            if (tarea.isCompleted()) {
                tareasCompletadas.add(tarea);
            }
        }

        return tareasCompletadas;
    }
}

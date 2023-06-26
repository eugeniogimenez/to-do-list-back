package logica;

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
}

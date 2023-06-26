package persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Task;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    TaskJpaController taskjpacontroller = new TaskJpaController();

    //CREATE
    public void crearTarea(Task task) {

        taskjpacontroller.create(task);
    }

    //READ
    public Task obtenerTarea(int id) {

        return taskjpacontroller.findTask(id);
    }

    //UPDATE
    public void actualizarTarea(Task tarea) {

        try {
            taskjpacontroller.edit(tarea);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //DESTROY
    public void eliminarTarea(int id) {

        try {
            taskjpacontroller.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

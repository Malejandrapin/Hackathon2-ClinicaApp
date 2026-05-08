package clinica.model;

import clinica.interfaces.Registrable;

public class Medico implements Registrable {
    private final int id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;

    public Medico(String nombre, String apellido, Especialidad especialidad) {
        setNombre(nombre);
        setApellido(apellido);
        setEspecialidad(especialidad);
    }

    public Medico(int id, String nombre, String apellido, Especialidad especialidad) {
        this.id = id;
        setNombre(nombre);
        setApellido(apellido);
        setEspecialidad(especialidad);
    }

}

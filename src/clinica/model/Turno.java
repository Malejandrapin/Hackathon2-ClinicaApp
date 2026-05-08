package clinica.model;

import java.time.LocalDateTime;

public class Turno {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private EstadoTurno estado;

    public Turno(
            int id,
            Paciente paciente,
            Medico medico,
            LocalDateTime fechaHora,
            EstadoTurno estado
    ) {
        this.id = id;
        setPaciente(paciente);
        setMedico(medico);
        setFechaHora(fechaHora);
        setEstado(estado);
    }

    public Turno(
            Paciente paciente,
            Medico medico,
            LocalDateTime fechaHora
    ) {
        setPaciente(paciente);
        setMedico(medico);
        setFechaHora(fechaHora);
        setEstado(EstadoTurno.PENDIENTE);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Debe haber un paciente asociado al turno.");
        }
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("Debe haber un medico asociado al turno.");
        }
        this.medico = medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new IllegalArgumentException("Debe haber una fecha y hora asociada al turno.");
        }
        this.fechaHora = fechaHora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turno)) return false;
        Turno turno = (Turno) o;
        return medico.equals(turno.medico) && fechaHora.equals(turno.fechaHora);
    }

    public String toString(){
        return estado.toString() + " " +
                paciente.getNombre() + " " +
                paciente.getApellido() +
                " — Dr. " + medico.getNombre() +
                " " + medico.getApellido() + " (" +
                medico.getEspecialidad() + ") — " +
                fechaHora;
    }
}

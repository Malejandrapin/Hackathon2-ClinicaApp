package clinica.service;

import clinica.interfaces.Consultable;
import clinica.model.EstadoTurno;
import clinica.model.Medico;
import clinica.model.Paciente;
import clinica.model.Turno;

import java.time.LocalDate;
import java.util.*;

public class ClinicaService implements Consultable {

    private List<Medico> medicos = new ArrayList<>();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    // Métodos de Paciente
    public void registrarPaciente(Paciente paciente) {
        if (!paciente.esValido()) {
            System.out.println("No se pudo registrar, paciente no es válido.");
            return;
        }

        for (Paciente p : pacientes) {
            if (p.getCedula().equals(paciente.getCedula())){
                System.out.println("El paciente ya existe.");
                return;
            }
        }

        paciente.setId(pacientes.size() + 1);
        pacientes.add(paciente);
        System.out.println("Paciente registrado correctamente.");
    }

    public Paciente buscarPorCedula(String cedula) {
        for (Paciente paciente : pacientes) {
            if (Objects.equals(paciente.getCedula(), cedula)) {
                return paciente;
            }
        }
        return null;
    }

    public void listarPacientes() {
        System.out.println("Lista de pacientes:");
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            List<Paciente> pacientesCopia = new ArrayList<>(pacientes);
            pacientesCopia.sort(
                    Comparator
                            .comparing(Paciente::getApellido)
                            .thenComparing(Paciente::getNombre)
            );
            for (Paciente paciente : pacientesCopia) {
                System.out.println(paciente.toString());
            }
        }
    }

    // Métodos de Médico

    public void registrarMedico(Medico medico) {
        if (!medico.esValido()) {
            System.out.println("No se pudo registrar, medico no es válido.");
            return;
        }

        for (Medico m : medicos) {
            if (m.getEspecialidad().equals(medico.getEspecialidad()) &&
                    m.getApellido().equals(medico.getApellido()) &&
                    m.getNombre().equals(medico.getNombre())
            ){
                System.out.println("El medico ya existe.");
                return;
            }
        }

        medico.setId(medicos.size() + 1);
        medicos.add(medico);
        System.out.println("Medico registrado correctamente.");
    }

    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        for (Medico medico : medicos) {
            if (medico.getNombre().equalsIgnoreCase(nombre) && medico.getApellido().equalsIgnoreCase(apellido)) {
                return medico;
            }
        }
        return null;
    }

    public void listarMedicos(){
        System.out.println("Lista de médicos:");
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
        } else {
            List<Medico> medicosCopia = new ArrayList<>(medicos);
            medicosCopia.sort(
                    Comparator
                            .comparing(Medico::getEspecialidad)
                            .thenComparing(Medico::getApellido)
            );
            for (Medico medico : medicosCopia) {
                System.out.println(medico.toString());
            }
        }
    }

    // Métodos de Turno
    public void asignarTurno(Turno turno) {
        Paciente paciente = buscarPorCedula(turno.getPaciente().getCedula());
        Medico medico = buscarPorNombreApellido(turno.getMedico().getNombre(), turno.getMedico().getApellido());

        if (medico == null || paciente == null) {
            System.out.println("No se pudo asignar el turno: Médico o Paciente no encontrados.");
            return;
        }

        for (Turno t : turnos) {
            if (t.getMedico().equals(medico) && t.getFechaHora().equals(turno.getFechaHora())){
                System.out.println("El turno ya existe.");
                return;
            }
        }

        turno.setId(turnos.size() + 1);
        turnos.add(turno);
        System.out.println("Turno asignado correctamente");
    }

    public void cancelarTurno(int turnoId) {
        for (Turno turno : turnos) {
            if (turno.getId() == turnoId) {
                if (turno.getEstado() != EstadoTurno.PENDIENTE) {
                    System.out.println("No se puede cancelar el turno. Estado actual de torno: " + turno.getEstado());
                    return;
                }
                turno.setEstado(EstadoTurno.CANCELADO);
                System.out.println("Turno cancelado correctamente.");
                return;
            }
        }
        System.out.println("Turno no encontrado.");
    }

    public void cambiarEstadoTurno(int turnoId, EstadoTurno estado) {
        for (Turno turno : turnos) {
            if (turno.getId() == turnoId) {
                turno.setEstado(estado);
                System.out.println("Estado del turno cambiado correctamente.");
                return;
            }
        }
        System.out.println("Turno no encontrado.");
    }


    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        List<Turno> turnosDia = new ArrayList<>();

        for (Turno turno : turnos) {

            if (turno.getFechaHora().toLocalDate().equals(fecha)) {
                turnosDia.add(turno);
            }
        }

        turnosDia.sort(
                Comparator.comparing(Turno::getFechaHora)
        );

        return turnosDia;
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        List<Turno> turnosMedico = new ArrayList<>();

        for (Turno turno : turnos) {

            if (turno.getMedico().equals(medico)) {
                turnosMedico.add(turno);
            }
        }

        return turnosMedico;
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {

        List<Turno> turnosPaciente = new ArrayList<>();

        for (Turno turno : turnos) {

            if (turno.getPaciente().equals(paciente)) {
                turnosPaciente.add(turno);
            }
        }

        return turnosPaciente;
    }
}

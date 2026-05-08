package clinica.service;

import clinica.interfaces.Consultable;
import clinica.model.Medico;
import clinica.model.Paciente;
import clinica.model.Turno;

import java.time.LocalDate;
import java.util.*;

public class ClinicaService implements Consultable {

    private Set<Medico> medicos = new HashSet<>();
    private Set<Paciente> pacientes = new HashSet<>();
    private List<Turno> turnos = new ArrayList<>();

    public Set getMedicos() {
        return medicos;
    }

    public Set getPacientes() {
        return pacientes;
    }

    public List getTurnos() {
        return turnos;
    }

    // Métodos de Paciente
    public void registrarPaciente(Paciente paciente) {
        if (!paciente.esValido()) {
            System.out.println("No se pudo registrar, paciente no es válido.");
            return;
        }

        if (pacientes.contains(paciente)) {
            System.out.println("El paciente ya existe.");
            return;
        }

        paciente.setId(pacientes.size() + 1);
        pacientes.add(paciente);
        System.out.println("Paciente registrado correctamente.");
    }

    public Paciente buscarPacientePorCedula(String cedula) {
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
            System.out.println("No se pudo registrar, paciente no es válido.");
            return;
        }

        if (pacientes.contains(medico)) {
            System.out.println("El paciente ya existe.");
            return;
        }

        medico.setId(pacientes.size() + 1);
        pacientes.add(medico);
        System.out.println("Paciente registrado correctamente.");

    }


    @Override
    public List listarTurnosDelDia(LocalDate fecha) {
        // Todos los turnos cuya fechaHora corresponda a ese día, ordenados de
        //menor a mayor por hora. Lista vacía si no hay ninguno.
        return List.of();
    }

    @Override
    public List buscarPorMedico(Medico medico) {
        // Todos los turnos asignados al médico indicado. Usa el equals() de Medico.
        //Lista vacía si no hay ninguno
        return List.of();
    }

    @Override
    public List buscarPorPaciente(Paciente paciente) {
        // Todos los turnos del paciente indicado. Usa el equals() de Paciente. Lista
        //vacía si no hay ninguno
        return List.of();
    }
}

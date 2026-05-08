package clinica.service;

import clinica.interfaces.Consultable;
import clinica.model.Medico;
import clinica.model.Paciente;
import clinica.model.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClinicaService implements Consultable {

    private List medicos = new ArrayList<Medico>();
    private List pacientes = new ArrayList<Paciente>();
    private List turnos = new ArrayList<Turno>();

    public List getMedicos() {
        return medicos;
    }

    public List getPacientes() {
        return pacientes;
    }

    public List getTurnos() {
        return turnos;
    }

    // Métodos de Paciente
    public void registrarPaciente(Paciente paciente) {
        if (!paciente.esValido()) {
            System.out.println("No se pudo registrar, paciente no es válido.");
        } else {
            if (pacientes.contains(paciente)) {
                System.out.println("No se pudo registrar, paciente ya existe.");
            }
        }
    }

    //

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

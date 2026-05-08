package clinica.interfaces;

import clinica.model.Medico;
import clinica.model.Paciente;

import java.time.LocalDate;
import java.util.List;

public interface Consultable {
    List listarTurnosDelDia(LocalDate fecha);
    List buscarPorMedico(Medico medico);
    List buscarPorPaciente(Paciente paciente);
}

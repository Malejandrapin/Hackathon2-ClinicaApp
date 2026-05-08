import clinica.datos.DatosCSV;
import clinica.model.*;
import clinica.service.ClinicaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClinicaService servicio = new ClinicaService();
        DatosCSV.cargar(servicio);

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("****************************************");
            System.out.println(" CLINICAAPP - MENU");
            System.out.println("========================================");
            System.out.println(" 1. Registrar paciente");
            System.out.println(" 2. Registrar médico");
            System.out.println(" 3. Asignar turno");
            System.out.println(" 4. Listar turnos del día");
            System.out.println(" 5. Cancelar turno");
            System.out.println(" 6. Ver turnos por médico");
            System.out.println(" 7. Ver turnos por paciente");
            System.out.println(" 8. Cambiar estado de turno");
            System.out.println(" 9. Listar pacientes");
            System.out.println(" 10. Listar médicos");
            System.out.println(" 0. Salir");
            System.out.printf("========================================\n\n");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                // REGISTRAR PACIENTE
                case 1:
                    try {
                        System.out.print("Cédula: ");
                        String cedula = sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();

                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();

                        Paciente paciente = new Paciente(
                                cedula,
                                nombre,
                                apellido,
                                telefono
                        );
                        servicio.registrarPaciente(paciente);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // REGISTRAR MEDICO

                case 2:
                    try {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();

                        System.out.println("Especialidades:");

                        for (Especialidad esp : Especialidad.values()) {
                            System.out.println("- " + esp);
                        }

                        System.out.print("Especialidad: ");

                        Especialidad especialidad =
                                Especialidad.valueOf(
                                        sc.nextLine().toUpperCase()
                                );

                        Medico medico = new Medico(
                                nombre,
                                apellido,
                                especialidad
                        );

                        servicio.registrarMedico(medico);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                // ASIGNAR TURNO
                case 3:
                    try {
                        System.out.print("Cédula paciente: ");
                        String cedulaPaciente = sc.nextLine();

                        Paciente paciente =
                                servicio.buscarPorCedula(cedulaPaciente);

                        if (paciente == null) {
                            System.out.println("Paciente no encontrado.");
                            break;
                        }

                        System.out.print("Nombre médico: ");
                        String nombreMedico = sc.nextLine();

                        System.out.print("Apellido médico: ");
                        String apellidoMedico = sc.nextLine();

                        Medico medico =
                                servicio.buscarPorNombreApellido(
                                        nombreMedico,
                                        apellidoMedico
                                );

                        if (medico == null) {

                            System.out.println("Médico no encontrado.");
                            break;
                        }

                        System.out.print("Año: ");
                        int anio = Integer.parseInt(sc.nextLine());

                        System.out.print("Mes: ");
                        int mes = Integer.parseInt(sc.nextLine());

                        System.out.print("Día: ");
                        int dia = Integer.parseInt(sc.nextLine());

                        System.out.print("Hora: ");
                        int hora = Integer.parseInt(sc.nextLine());

                        System.out.print("Minuto: ");
                        int minuto = Integer.parseInt(sc.nextLine());

                        LocalDateTime fechaHora =
                                LocalDateTime.of(
                                        anio,
                                        mes,
                                        dia,
                                        hora,
                                        minuto
                                );

                        Turno turno = new Turno(
                                paciente,
                                medico,
                                fechaHora
                        );

                        servicio.asignarTurno(turno);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                // LISTAR TURNOS DEL DIA

                case 4:

                    try {
                        System.out.print("Año: ");
                        int anio = Integer.parseInt(sc.nextLine());

                        System.out.print("Mes: ");
                        int mes = Integer.parseInt(sc.nextLine());

                        System.out.print("Día: ");
                        int dia = Integer.parseInt(sc.nextLine());

                        LocalDate fecha =
                                LocalDate.of(anio, mes, dia);

                        List<Turno> turnosDia =
                                servicio.listarTurnosDelDia(fecha);

                        if (turnosDia.isEmpty()) {

                            System.out.println("No hay turnos.");
                        }

                        for (Turno t : turnosDia) {

                            System.out.println(t);
                        }

                    } catch (Exception e) {

                        System.out.println("Fecha inválida.");
                    }

                    break;

                // CANCELAR TURNO

                case 5:

                    System.out.print("ID turno: ");

                    int idCancelar =
                            Integer.parseInt(sc.nextLine());

                    servicio.cancelarTurno(idCancelar);

                    break;

                // TURNOS POR MEDICO

                case 6:

                    System.out.print("Nombre médico: ");
                    String nomMed = sc.nextLine();

                    System.out.print("Apellido médico: ");
                    String apeMed = sc.nextLine();

                    Medico medicoBuscado =
                            servicio.buscarPorNombreApellido(
                                    nomMed,
                                    apeMed
                            );

                    if (medicoBuscado == null) {

                        System.out.println("Médico no encontrado.");
                        break;
                    }

                    List<Turno> turnosMedico =
                            servicio.buscarPorMedico(medicoBuscado);

                    for (Turno t : turnosMedico) {

                        System.out.println(t);
                    }

                    break;

                // TURNOS POR PACIENTE

                case 7:
                    System.out.print("Cédula paciente: ");
                    String ced = sc.nextLine();

                    Paciente pacienteBuscado =
                            servicio.buscarPorCedula(ced);

                    if (pacienteBuscado == null) {
                        System.out.println("Paciente no encontrado.");
                        break;
                    }

                    List<Turno> turnosPaciente =
                            servicio.buscarPorPaciente(pacienteBuscado);

                    for (Turno t : turnosPaciente) {
                        System.out.println(t);
                    }

                    break;

                // CAMBIAR ESTADO
                case 8:

                    try {
                        System.out.print("ID turno: ");
                        int idTurno =
                                Integer.parseInt(sc.nextLine());

                        System.out.println("Estados:");

                        for (EstadoTurno est :
                                EstadoTurno.values()) {

                            System.out.println("- " + est);
                        }

                        System.out.print("Nuevo estado: ");

                        EstadoTurno nuevo =
                                EstadoTurno.valueOf(
                                        sc.nextLine().toUpperCase()
                                );

                        servicio.cambiarEstadoTurno(
                                idTurno,
                                nuevo
                        );

                    } catch (Exception e) {

                        System.out.println("Estado inválido.");
                    }

                    break;

                // LISTAR PACIENTES

                case 9:
                    servicio.listarPacientes();
                    break;

                // LISTAR MEDICOS
                case 10:
                    servicio.listarMedicos();
                    break;

                // SALIR

                case 0:

                    DatosCSV.guardar(servicio);
                    System.out.println(
                            "Hasta pronto. Datos guardados."
                    );
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
        sc.close();
    }
}
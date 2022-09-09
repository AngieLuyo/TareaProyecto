package pe.com.nttdata.empleado.controller;

public record EmpleadoRequest(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String dni, String edad, String email, java.time.LocalDate fechaNacimiento) {
}

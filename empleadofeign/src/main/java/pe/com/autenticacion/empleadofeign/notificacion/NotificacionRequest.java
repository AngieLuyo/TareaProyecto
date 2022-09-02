package pe.com.autenticacion.empleadofeign.notificacion;

public record NotificacionRequest(Integer empleadoId, String empleadoEmail, String mensaje) {
}

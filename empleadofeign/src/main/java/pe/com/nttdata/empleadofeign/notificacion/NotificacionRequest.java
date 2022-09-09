package pe.com.nttdata.empleadofeign.notificacion;

public record NotificacionRequest(Integer empleadoId, String empleadoEmail, String mensaje) {
}

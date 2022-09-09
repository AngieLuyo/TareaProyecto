package pe.com.nttdata.notificacion.service;

import pe.com.nttdata.empleadofeign.notificacion.NotificacionRequest;

public interface INotificacionService {
    public boolean enviarNotificacion(NotificacionRequest notificacionRequest);
}

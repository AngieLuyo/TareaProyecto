package pe.com.autenticacion.notificacion.service;

import pe.com.autenticacion.empleadofeign.notificacion.NotificacionRequest;

public interface INotificacionService {
    public boolean enviarNotificacion(NotificacionRequest notificacionRequest);
}

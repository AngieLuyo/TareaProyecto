package pe.com.autenticacion.notificacion.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.autenticacion.empleadofeign.notificacion.NotificacionRequest;
import pe.com.autenticacion.notificacion.model.Notificacion;
import pe.com.autenticacion.notificacion.dao.INotificacionDao;
import pe.com.autenticacion.notificacion.service.INotificacionService;

import java.util.Date;

@Service
@AllArgsConstructor
public class NotificacionService implements INotificacionService {
    private final INotificacionDao notificacionDao;

    public boolean enviarNotificacion(NotificacionRequest notificacionRequest) {
        notificacionDao.save(
                Notificacion.builder()
                        .empleadoId(notificacionRequest.empleadoId())
                        .empleadoEmail(notificacionRequest.empleadoEmail())
                        .remitente("NTTData")
                        .mensaje(notificacionRequest.mensaje())
                        .horaEnvio(new Date())
                        .build()
        );
        return false;
    }
}

package pe.com.autenticacion.notificacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.autenticacion.notificacion.model.Notificacion;

public interface INotificacionDao extends JpaRepository<Notificacion, Integer> {
}

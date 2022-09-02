package pe.com.autenticacion.validar.empleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.autenticacion.validar.empleado.model.EmpleadoCheck;

public interface IEmpleadoCheckDao extends JpaRepository<EmpleadoCheck, Integer> {
}

package pe.com.nttdata.validar.empleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.validar.empleado.model.EmpleadoCheck;

public interface IEmpleadoCheckDao extends JpaRepository<EmpleadoCheck, Integer> {
}

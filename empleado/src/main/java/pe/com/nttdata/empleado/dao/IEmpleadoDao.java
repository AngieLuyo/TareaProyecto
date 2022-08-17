package pe.com.nttdata.empleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.empleado.model.Empleado;

import java.time.LocalDate;
import java.util.List;

public interface IEmpleadoDao extends JpaRepository<Empleado, Integer> {

    List<Empleado> findByNombre(String nombre);
    List<Empleado> findByApellidoPaterno(String apellidoPaterno);
    List<Empleado> findByApellidoMaterno(String apellidoMaterno);
    List<Empleado> findByFechaNacimiento(LocalDate fechaNacimiento);
}

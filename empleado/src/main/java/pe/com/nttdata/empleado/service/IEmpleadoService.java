package pe.com.nttdata.empleado.service;

import pe.com.nttdata.empleado.model.Empleado;

import java.time.LocalDate;
import java.util.List;

public interface IEmpleadoService {
    public List<Empleado> listarEmpleados();
    public Empleado registrarEmpleado(Empleado empleado);
    public String validarEmpleado(Empleado empleado);
    public void registrarNotificacion(Empleado empleado);
    public Empleado modificarEmpleado(Empleado empleado);
    public int eliminarEmpleado(Integer id);
    public Empleado obtenerEmpleado(Integer id);
    public List<Empleado> listarEmpleadosPorNombre(String nombre);
    public List<Empleado> listarEmpleadosPorApellidoPaterno(String apellidoPaterno);
    public List<Empleado> listarEmpleadosPorApellidoMaterno(String apellidoMaterno);
    public List<Empleado> listarEmpleadosPorFechaNacimiento(LocalDate fechaNacimiento);
}

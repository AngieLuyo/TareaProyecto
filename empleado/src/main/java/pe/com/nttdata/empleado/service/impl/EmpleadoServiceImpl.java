package pe.com.nttdata.empleado.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.nttdata.empleado.controller.EmpleadoRequest;
import pe.com.nttdata.empleado.dao.IEmpleadoDao;
import pe.com.nttdata.empleado.model.Empleado;
import pe.com.nttdata.empleado.service.IEmpleadoService;
import pe.com.nttdata.empleadofeign.notificacion.NotificacionRequest;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckClient;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckResponse;
import pe.com.nttdata.empleadoqueues.rabbitmq.RabbitMQMessageProducer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final IEmpleadoDao empleadoDao;
    //private final RestTemplate restTemplate;

    private final EmpleadoCheckClient empleadoCheckClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public List<Empleado> listarEmpleados() {
        return empleadoDao.findAll();
    }

    public Empleado registrarEmpleado(Empleado empleado) {
        /*empleado empleado = empleado.builder()
                .nombre(empleadoRequest.nombre())
                .apellidoPaterno(empleadoRequest.apellidoPaterno())
                .apellidoMaterno(empleadoRequest.apellidoMaterno())
                .email(empleadoRequest.email())
                .fechaNacimiento(empleadoRequest.fechaNacimiento())
                .build();*/
        Empleado empleadoResponse = empleadoDao.save(empleado);

        /*empleadoCheckResponse empleadoCheckResponse = restTemplate.getForObject(
                //"http://localhost:8081/api/v1/empleado-check/{empleadoId}",
                "http://VALIDARempleado/api/v1/empleado-check/{empleadoId}",
                empleadoCheckResponse.class,
                empleadoResponse.getId()
        );*/

        EmpleadoCheckResponse empleadoCheckResponse = empleadoCheckClient.validarEmpleado(empleadoResponse.getId());

        if (empleadoCheckResponse.esEstafador()) {
            throw new IllegalStateException("Empleado es un estafador!!");
        }

        NotificacionRequest notificacionRequest = new NotificacionRequest(
                empleado.getId(),
                empleado.getEmail(),
                String.format("Hola %s, bienvenidos a NTTData...",
                        empleado.getNombre())
        );

        rabbitMQMessageProducer.publish(
                notificacionRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        return empleadoResponse;
    }

    public Empleado modificarEmpleado(Empleado empleado) {
        /*empleado empleado = empleado.builder()
                .id(empleadoRequest.id())
                .nombre(empleadoRequest.nombre())
                .apellidoPaterno(empleadoRequest.apellidoPaterno())
                .apellidoMaterno(empleadoRequest.apellidoMaterno())
                .email(empleadoRequest.email())
                .fechaNacimiento(empleadoRequest.fechaNacimiento())
                .build();*/
        return empleadoDao.save(empleado);
    }

    public int eliminarEmpleado(Integer id) {
        empleadoDao.deleteById(id);
        return 0;
    }

    public Empleado obtenerEmpleado(Integer id) {
        return empleadoDao.findById(id).get();
    }

    public List<Empleado> listarEmpleadosPorNombre(String nombre) {
        return empleadoDao.findByNombre(nombre);
    }

    public List<Empleado> listarEmpleadosPorApellidoPaterno(String apellidoPaterno) {
        return empleadoDao.findByApellidoPaterno(apellidoPaterno);
    }

    public List<Empleado> listarEmpleadosPorApellidoMaterno(String apellidoMaterno) {
        return empleadoDao.findByApellidoMaterno(apellidoMaterno);
    }

    public List<Empleado> listarEmpleadosPorFechaNacimiento(LocalDate fechaNacimiento) {
        return empleadoDao.findByFechaNacimiento(fechaNacimiento);
    }
}

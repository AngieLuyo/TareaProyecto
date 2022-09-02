package pe.com.autenticacion.empleado.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.autenticacion.empleadofeign.validar.empleado.EmpleadoCheckClient;
import pe.com.autenticacion.empleadofeign.validar.empleado.EmpleadoCheckResponse;
import pe.com.autenticacion.empleadoqueues.rabbitmq.RabbitMQMessageProducer;
import pe.com.autenticacion.empleado.dao.IEmpleadoDao;
import pe.com.autenticacion.empleado.model.Empleado;
import pe.com.autenticacion.empleado.service.IEmpleadoService;
import pe.com.autenticacion.empleadofeign.notificacion.NotificacionRequest;

import java.time.LocalDate;
import java.util.List;

@Slf4j
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

        return empleadoResponse;
    }


    @CircuitBreaker(name = "validarempleadoCB", fallbackMethod = "fallValidarempleadoCB")
    @Retry(name = "validarempleadoRetry")
    public String validarEmpleado(Empleado empleado) {
        log.info("Estoy en metodo validarEmpleado");
        EmpleadoCheckResponse empleadoCheckResponse = empleadoCheckClient.validarEmpleado(empleado.getId());

        if (empleadoCheckResponse.esEstafador()) {
            throw new IllegalStateException("Empleado es un estafador!!");
        }

        return "OK";
    }

    public String fallValidarempleadoCB(Empleado empleado, Exception e) {
        return "NO_OK";
    }

    public void registrarNotificacion(Empleado empleado) {

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

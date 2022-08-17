package pe.com.nttdata.empleado.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.empleado.model.Empleado;
import pe.com.nttdata.empleado.service.IEmpleadoService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/empleado")
@AllArgsConstructor
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<?> listarEmpleados() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        log.info("listar empleados");
        return new ResponseEntity<>(empleados, empleados.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obtenerEmpleado(@PathVariable("id") Integer id) {
        log.info("obtener empleado: ", id);
        return new ResponseEntity<>(empleadoService.obtenerEmpleado(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarEmpleado(@Valid @RequestBody Empleado empleado) {
        log.info("nuevo registro de empleado {}", empleado);
        Empleado newEmpleado = empleadoService.registrarEmpleado(empleado);
        return new ResponseEntity<EmpleadoRequest>(new EmpleadoRequest(newEmpleado.getId(), empleado.getNombre(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno() , empleado.getDni() , empleado.getEdad(), empleado.getEmail(), empleado.getFechaNacimiento()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modificarEmpleado(@Valid @RequestBody Empleado empleado) {
        log.info("modificar datos de empleado {}", empleado);
        empleadoService.modificarEmpleado(empleado);
        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarEmpleado(@PathVariable("id") Integer id) {
        log.info("eliminar empleado: ", id);
        empleadoService.eliminarEmpleado(id);
    }

    @GetMapping(params="nombre")
    public ResponseEntity<?> listarEmpleadosPorNombre(@RequestParam String nombre) {
        List<Empleado> empleados = empleadoService.listarEmpleadosPorNombre(nombre);
        log.info("listar empleados por nombre: ",nombre);
        return new ResponseEntity<>(empleados, empleados.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="apellidoPaterno")
    public ResponseEntity<?> listarEmpleadosPorApellidoPaterno(@RequestParam String apellidoPaterno) {
        List<Empleado> empleados = empleadoService.listarEmpleadosPorApellidoPaterno(apellidoPaterno);
        log.info("listar empleados por apellido paterno: ", apellidoPaterno);
        return new ResponseEntity<>(empleados, empleados.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="apellidoMaterno")
    public ResponseEntity<?> listarEmpleadosPorApellidoMaterno(@RequestParam String apellidoMaterno) {
        List<Empleado> empleados = empleadoService.listarEmpleadosPorApellidoMaterno(apellidoMaterno);
        log.info("listar empleados por apellido materno:", apellidoMaterno);
        return new ResponseEntity<>(empleados, empleados.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="fechaNacimiento")
    public ResponseEntity<?> listarEmpleadosPorFechaNacimiento(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento) {
        List<Empleado> empleados = empleadoService.listarEmpleadosPorFechaNacimiento(fechaNacimiento);
        log.info("listar empleados por fecha de nacimiento: ", fechaNacimiento);
        return new ResponseEntity<>(empleados, empleados.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}

package pe.com.nttdata.validar.empleado.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckResponse;
import pe.com.nttdata.validar.empleado.service.IEmpleadoCheckService;

@Slf4j
@RestController
@RequestMapping("api/v1/empleado-check")
@AllArgsConstructor
public class EmpleadoCheckController {
    private final IEmpleadoCheckService empleadoCheckService;

    @GetMapping(path = "{empleadoId}")
    public EmpleadoCheckResponse validarEmpleado(
            @PathVariable("empleadoId") Integer empleadoId) {
        boolean esEstafador = empleadoCheckService.
                esEmpleadoFraudulento(empleadoId);
        log.info("validacion para empleado: {}", empleadoId);

        return new EmpleadoCheckResponse(esEstafador);
    }
}

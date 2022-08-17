package pe.com.nttdata.empleadofeign.validar.empleado;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
       name = "validarempleado",
       url = "${empleadofeign.validarempleado.url}" )
public interface EmpleadoCheckClient {
    @GetMapping(path = "api/v1/empleado-check/{empleadoId}")
    EmpleadoCheckResponse validarEmpleado(@PathVariable("empleadoId") Integer empleadoId);
}

package pe.com.autenticacion.validar.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(
        basePackages = "pe.com.nttdata.empleadofeign"
)
public class EmpleadoCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpleadoCheckApplication.class, args);
    }
}

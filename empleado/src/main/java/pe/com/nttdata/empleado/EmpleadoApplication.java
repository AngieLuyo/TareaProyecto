package pe.com.nttdata.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "pe.com.nttdata.empleado",
                "pe.com.nttdata.empleadoqueues"
        }
)
//@ComponentScan(basePackageClasses = EmpleadoController.class)
@EnableFeignClients(
        basePackages = "pe.com.nttdata.empleadofeign"
)
@PropertySources({
        @PropertySource("classpath:empleadofeign-${spring.profiles.active}.properties")
})
public class EmpleadoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpleadoApplication.class, args);
    }
}
